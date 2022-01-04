package com.hjw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjw.common.exception.BaseException;
import com.hjw.domain.dto.SearchDto;
import com.hjw.domain.entity.Resource;
import com.hjw.domain.vo.WebItemVo;
import com.hjw.mapper.ResourceMapper;
import com.hjw.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public boolean addResource(Resource resource) {
        Resource exist = getOne(new LambdaQueryWrapper<Resource>().eq(Resource::getName, resource.getName())
                .eq(Resource::getUserId, resource.getUserId()).eq(Resource::getType, resource.getType()));
        if(Objects.nonNull(exist)){
            throw new BaseException("该资源名称已经存在！");
        }
        return save(resource);
    }

    @Override
    public IPage<Resource> searchResourceByUserId(Page<Resource> page, SearchDto searchDto) {
        return resourceMapper.searchResourceByUserId(page,searchDto);
    }
}
