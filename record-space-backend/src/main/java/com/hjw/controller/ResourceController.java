package com.hjw.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjw.common.constant.BaseConstant;
import com.hjw.common.response.Result;
import com.hjw.domain.dto.SearchDto;
import com.hjw.domain.entity.Resource;
import com.hjw.domain.vo.WebItemVo;
import com.hjw.service.ResourceService;
import com.hjw.util.ShiroUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hjw
 * @since 2021-10-15
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @PostMapping("/add")
    @RequiresAuthentication
    @ApiOperation(value = "新增资源")
    public Result addResource(@Validated @RequestBody Resource resource){
        resource.setUserId(ShiroUtils.getAuthUser().getId());
        boolean res =resourceService.addResource(resource);
        return res?Result.ofSuccess():Result.ofFail();
    }

    @ApiOperation("查询用户所有文章")
    @RequiresAuthentication
    @PostMapping("/searchByUserId")
    public Result searchResourceByUserId(@RequestBody SearchDto searchDto){
        if(Objects.isNull(searchDto.getCurrentPage()) || searchDto.getCurrentPage()<1){
            searchDto.setCurrentPage(BaseConstant.PAGE_INIT);
        }
        if(Objects.isNull(searchDto.getPageSize()) || searchDto.getPageSize()<=0){
            searchDto.setPageSize(BaseConstant.WEB_PAGE_SIZE);
        }
        Page<Resource> page = new Page<>(searchDto.getCurrentPage(),searchDto.getPageSize());
        searchDto.setUserId(ShiroUtils.getAuthUser().getId());

        return Result.ofSuccess(resourceService.searchResourceByUserId(page,searchDto));
    }
}
