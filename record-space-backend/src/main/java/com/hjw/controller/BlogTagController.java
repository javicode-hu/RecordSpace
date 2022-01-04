package com.hjw.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjw.common.constant.BaseConstant;
import com.hjw.common.response.Result;
import com.hjw.domain.dto.SearchDto;
import com.hjw.domain.entity.SysUser;
import com.hjw.domain.vo.TagItemVo;
import com.hjw.service.BlogTagService;
import com.hjw.domain.entity.BlogTag;
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
@RequestMapping("/tag")
public class BlogTagController {


    @Autowired
    BlogTagService blogTagService;

    @PostMapping("/add")
    @RequiresAuthentication
    @ApiOperation("添加标签")
    public Result addBlogTag(@Validated @RequestBody BlogTag blogTag){
        SysUser authUser = ShiroUtils.getAuthUser();
        blogTag.setUserId(authUser.getId());
        boolean res = blogTagService.addBlogTag(blogTag);
        return res?Result.ofSuccess():Result.ofFail();
    }

    @GetMapping("/listByUserId")
    @RequiresAuthentication
    @ApiOperation("获取某用户所有标签")
    public Result listBlogTagByUserId(){
        SysUser authUser = ShiroUtils.getAuthUser();
        return Result.ofSuccess(blogTagService.listBlogTagByUserId(authUser.getId()));

    }


    @ApiOperation("查询用户所有标签")
    @RequiresAuthentication
    @PostMapping("/searchByUserId")
    public Result searchBlogTagByUserId(@RequestBody SearchDto searchDto){
        if(Objects.isNull(searchDto.getCurrentPage()) || searchDto.getCurrentPage()<1){
            searchDto.setCurrentPage(BaseConstant.PAGE_INIT);
        }
        if(Objects.isNull(searchDto.getPageSize()) || searchDto.getPageSize()<=0){
            searchDto.setPageSize(BaseConstant.TAG_PAGE_SIZE);
        }
        Page<TagItemVo> page = new Page<>(searchDto.getCurrentPage(), searchDto.getPageSize());
        searchDto.setUserId( ShiroUtils.getAuthUser().getId());
        return Result.ofSuccess(blogTagService.searchBlogTagByUserId(page,searchDto));
    }


    @GetMapping("/getById/{id}")
    @RequiresAuthentication
    @ApiOperation(value = "根据id查询标签")
    public Result getBlogTagById(@PathVariable(value = "id") Integer id){
        return Result.ofSuccess(blogTagService.getBlogTagById(id));
    }

}
