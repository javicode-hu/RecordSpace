package com.hjw.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjw.domain.dto.SearchDto;
import com.hjw.domain.entity.BlogTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjw.domain.vo.TagItemVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjw
 * @since 2021-10-15
 */
@Mapper
@Component
public interface BlogTagMapper extends BaseMapper<BlogTag> {

    IPage<TagItemVo> searchBlogTagByUserId(Page<?> page, SearchDto searchDto);

    TagItemVo getBlogTagById(Integer id);

}
