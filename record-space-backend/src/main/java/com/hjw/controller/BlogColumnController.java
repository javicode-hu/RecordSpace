package com.hjw.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjw.common.constant.BaseConstant;
import com.hjw.common.response.Result;
import com.hjw.domain.dto.SearchDto;
import com.hjw.domain.entity.BlogColumn;
import com.hjw.domain.entity.SysUser;
import com.hjw.domain.vo.ColumnItemVo;
import com.hjw.service.BlogColumnService;
import com.hjw.util.ShiroUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/column")
public class BlogColumnController {

    @Autowired
    BlogColumnService blogColumnService;

    @PostMapping("/add")
    @RequiresAuthentication
    @ApiOperation(value = "新增专栏")
    public Result addBlogColumn(@Validated @RequestBody BlogColumn blogColumn){
        SysUser authUser = ShiroUtils.getAuthUser();
        blogColumn.setUserId(authUser.getId());
        boolean res = blogColumnService.addBlogColumn(blogColumn);
        return res?Result.ofSuccess():Result.ofFail();

    }

    @GetMapping("/listByUserId")
    @RequiresAuthentication
    @ApiOperation(value = "获取某用户所有专栏")
    public Result listBlogColumnByUserId(){
        SysUser authUser = ShiroUtils.getAuthUser();
        return Result.ofSuccess(blogColumnService.listBlogColumnByUserId(authUser.getId()));
    }

    @PostMapping("/searchByUserId")
    @RequiresAuthentication
    @ApiOperation(value = "查询某用户所有专栏")
    public Result searchBlogColumnByUserId(@RequestBody SearchDto searchDto){
        if(Objects.isNull(searchDto.getCurrentPage()) || searchDto.getCurrentPage()<1){
            searchDto.setCurrentPage(BaseConstant.PAGE_INIT);
        }
        if(Objects.isNull(searchDto.getPageSize()) || searchDto.getPageSize()<=0){
            searchDto.setPageSize(BaseConstant.COLUMN_PAGE_SIZE);
        }
        Page<ColumnItemVo> page = new Page<>(searchDto.getCurrentPage(), searchDto.getPageSize());
        searchDto.setUserId(ShiroUtils.getAuthUser().getId());
        return Result.ofSuccess(blogColumnService.searchBlogColumnByUserId(page,searchDto));
    }

    @GetMapping("/getById/{id}")
    @RequiresAuthentication
    @ApiOperation(value = "根据id查询专栏")
    public Result getBlogColumnById(@PathVariable(value = "id") Integer id){
        return Result.ofSuccess(blogColumnService.getBlogColumnById(id));
    }

}
