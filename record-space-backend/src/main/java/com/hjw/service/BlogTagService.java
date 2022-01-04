package com.hjw.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjw.domain.dto.SearchDto;
import com.hjw.domain.entity.BlogTag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjw.domain.vo.TagItemVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjw
 * @since 2021-10-15
 */
public interface BlogTagService extends IService<BlogTag> {

    boolean addBlogTag(BlogTag blogTag);

    List<BlogTag> listBlogTagByUserId(Integer userId);

    /**
     * 获取某用户所有标签
     * @param page
     * @return
     */
    IPage<TagItemVo> searchBlogTagByUserId(Page<TagItemVo> page, SearchDto searchDto);


    /**
     * 根据id获取标签信息
     * @param id
     * @return
     */
    TagItemVo getBlogTagById(Integer id);

}
