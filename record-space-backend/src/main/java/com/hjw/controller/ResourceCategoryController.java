package com.hjw.controller;


import com.hjw.common.response.Result;
import com.hjw.domain.entity.ResourceCategory;
import com.hjw.domain.entity.SysUser;
import com.hjw.service.ResourceCategoryService;
import com.hjw.util.ShiroUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hjw
 * @since 2021-10-15
 */
@RestController
@RequestMapping("/category")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;




    @GetMapping("/tree/{type}")
    @ApiOperation("获取某类型目录树")
    @RequiresAuthentication
    public Result getCategoryTreeByTypeAndUserId(@PathVariable(value = "type") String type){
        SysUser authUser = ShiroUtils.getAuthUser();
        return Result.ofSuccess(resourceCategoryService.getCategoryTreeByTypeAndUserId(type,authUser.getId()));
    }


    @PostMapping("/add")
    @ApiOperation("添加类名")
    @RequiresAuthentication
    public Result addCategory(@Validated @RequestBody ResourceCategory category){
        SysUser authUser = ShiroUtils.getAuthUser();
        category.setUserId(authUser.getId());
        return Result.ofSuccess(resourceCategoryService.addCategory(category));
    }

}
