package com.hjw.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjw.domain.dto.ArticleAddDto;
import com.hjw.domain.dto.SearchDto;
import com.hjw.domain.entity.*;
import com.hjw.domain.vo.ArticleDetailVo;
import com.hjw.domain.vo.ArticleItemVo;
import com.hjw.domain.vo.BlogAsideVo;
import com.hjw.mapper.BlogArticleMapper;
import com.hjw.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjw
 * @since 2021-10-15
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {


    @Autowired
    BlogArticleMapper blogArticleMapper;

    @Autowired
    ArticleRelatedColumnService articleRelatedColumnService;

    @Autowired
    ArticleRelatedTagService articleRelatedTagService;

    @Autowired
    BlogColumnService blogColumnService;

    @Autowired
    BlogTagService blogTagService;

    @Override
    public boolean addBlogArticle(ArticleAddDto articleAddDto) {
        BlogArticle blogArticle = new BlogArticle();
        BeanUtil.copyProperties(articleAddDto, blogArticle);
        if(save(blogArticle)){
            //批量插入关联专栏
            List<ArticleRelatedColumn> columnCollect = articleAddDto.getColumnIdList()
                    .stream()
                    .map(columnId -> ArticleRelatedColumn.builder().articleId(blogArticle.getId()).columnId(columnId).build())
                    .collect(Collectors.toList());
            articleRelatedColumnService.saveBatch(columnCollect);

            //批量插入关联标签
            List<ArticleRelatedTag> tagCollect = articleAddDto.getTagIdList()
                    .stream()
                    .map(tagId -> ArticleRelatedTag.builder().articleId(blogArticle.getId()).tagId(tagId).build())
                    .collect(Collectors.toList());
            articleRelatedTagService.saveBatch(tagCollect);
            return true;
        }
        return false;
    }

    @Override
    public ArticleDetailVo getBlogArticleById(Integer id) {
        return blogArticleMapper.getBlogArticleById(id);
    }

    @Override
    public IPage<ArticleItemVo> searchBlogArticleByUserId(Page<ArticleItemVo> page,SearchDto searchDto) {
        return blogArticleMapper.searchBlogArticleByUserId(page,searchDto);
    }

    @Override
    public BlogAsideVo getBlogAsideInfo(Integer userId) {
        Integer articleCount = count(new LambdaQueryWrapper<BlogArticle>().eq(BlogArticle::getUserId, userId));
        Integer columnCount =  blogColumnService.count(new LambdaQueryWrapper<BlogColumn>().eq(BlogColumn::getUserId,userId));
        Integer tagCount = blogTagService.count(new LambdaQueryWrapper<BlogTag>().eq(BlogTag::getUserId,userId));
        BlogArticle recentArticle = getOne(new LambdaQueryWrapper<BlogArticle>()
                .eq(BlogArticle::getUserId,userId)
                .orderByDesc(BlogArticle::getCreateTime).last("limit 1"));
        BlogColumn recentColumn = blogColumnService.getOne(new LambdaQueryWrapper<BlogColumn>()
                .eq(BlogColumn::getUserId,userId)
                .orderByDesc(BlogColumn::getCreateTime).last("limit 1"));

        return BlogAsideVo.builder()
                .articleCount(articleCount)
                .columnCount(columnCount)
                .tagCount(tagCount)
                .recentArticle(recentArticle)
                .recentColumn(recentColumn)
                .build();
    }

}
