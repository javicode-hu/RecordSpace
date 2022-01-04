package com.hjw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjw.common.exception.BaseException;
import com.hjw.domain.dto.SearchDto;
import com.hjw.domain.entity.BlogTag;
import com.hjw.domain.vo.TagItemVo;
import com.hjw.mapper.BlogTagMapper;
import com.hjw.service.BlogTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjw
 * @since 2021-10-15
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    @Autowired
    BlogTagMapper blogTagMapper;


    @Override
    public boolean addBlogTag(BlogTag blogTag) {
        BlogTag exist = getOne(new LambdaQueryWrapper<BlogTag>().eq(BlogTag::getName, blogTag.getName()), false);
        if(Objects.nonNull(exist)){
            throw new BaseException("标签名称已经被使用啦");
        }

        return save(blogTag);
    }

    @Override
    public List<BlogTag> listBlogTagByUserId(Integer userId) {
        return list(new LambdaQueryWrapper<BlogTag>().eq(BlogTag::getUserId,userId));
    }

    @Override
    public IPage<TagItemVo> searchBlogTagByUserId(Page<TagItemVo> page, SearchDto searchDto) {
        return blogTagMapper.searchBlogTagByUserId(page,searchDto);
    }

    @Override
    public TagItemVo getBlogTagById(Integer id) {
        return blogTagMapper.getBlogTagById(id);
    }
}
