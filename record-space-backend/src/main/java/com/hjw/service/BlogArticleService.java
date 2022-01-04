package com.hjw.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjw.domain.dto.ArticleAddDto;
import com.hjw.domain.dto.SearchDto;
import com.hjw.domain.entity.BlogArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjw.domain.vo.ArticleDetailVo;
import com.hjw.domain.vo.ArticleItemVo;
import com.hjw.domain.vo.BlogAsideVo;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjw
 * @since 2021-10-15
 */
public interface BlogArticleService extends IService<BlogArticle> {

    /**
     * 添加文章
     * @param articleAddDto
     * @return
     */
    boolean addBlogArticle(ArticleAddDto articleAddDto);



    ArticleDetailVo getBlogArticleById(Integer id);

    /**
     * 获取某用户所有文章
     * @param page
     * @return
     */
    IPage<ArticleItemVo> searchBlogArticleByUserId(Page<ArticleItemVo>page,SearchDto searchDto);

    /**
     * 获取博客侧边栏信息
     * @return
     */
    BlogAsideVo getBlogAsideInfo(Integer userId);

}
