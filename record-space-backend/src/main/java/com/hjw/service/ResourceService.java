package com.hjw.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjw.domain.dto.SearchDto;
import com.hjw.domain.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjw.domain.vo.WebItemVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjw
 * @since 2021-10-15
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 添加资源
     * @param resource
     * @return
     */
    boolean addResource(Resource resource);

    IPage<Resource> searchResourceByUserId(Page<Resource> page, SearchDto searchDto);
}
