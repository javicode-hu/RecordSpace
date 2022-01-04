package com.hjw.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjw.domain.dto.SearchDto;
import com.hjw.domain.entity.BlogColumn;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjw.domain.vo.ColumnItemVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjw
 * @since 2021-10-15
 */
public interface BlogColumnService extends IService<BlogColumn> {

     boolean addBlogColumn(BlogColumn blogColumn);

     List<BlogColumn> listBlogColumnByUserId(Integer userId);

     IPage<ColumnItemVo> searchBlogColumnByUserId(Page<ColumnItemVo> page, SearchDto searchDto);

     ColumnItemVo getBlogColumnById(Integer id);
}
