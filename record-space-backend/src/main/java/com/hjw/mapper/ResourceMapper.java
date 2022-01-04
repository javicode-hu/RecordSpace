package com.hjw.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjw.domain.dto.SearchDto;
import com.hjw.domain.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjw.domain.vo.WebItemVo;
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
public interface ResourceMapper extends BaseMapper<Resource> {

    IPage<Resource> searchResourceByUserId(Page<?> page, SearchDto searchDto);
}
