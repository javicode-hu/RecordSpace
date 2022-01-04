package com.hjw.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjw.common.constant.BaseConstant;
import com.hjw.common.response.Result;
import com.hjw.domain.dto.ArticleAddDto;
import com.hjw.domain.dto.SearchDto;
import com.hjw.domain.entity.BlogArticle;
import com.hjw.domain.entity.SysUser;
import com.hjw.domain.vo.ArticleItemVo;
import com.hjw.service.BlogArticleService;
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
@RequestMapping("/article")
public class BlogArticleController {

    @Autowired
    BlogArticleService blogArticleService;

    @ApiOperation("添加文章")
    @RequiresAuthentication
    @PostMapping("/add")
    public Result addArticle(@Validated @RequestBody ArticleAddDto articleAddDto){
        SysUser authUser = ShiroUtils.getAuthUser();
        articleAddDto.setUserId(authUser.getId());
        boolean res = blogArticleService.addBlogArticle(articleAddDto);
        return res?Result.ofSuccess():Result.ofFail();
    }

    @ApiOperation("查询用户所有文章")
    @RequiresAuthentication
    @PostMapping("/searchByUserId")
    public Result searchArticleByUserId(@RequestBody SearchDto searchDto){
        if(Objects.isNull(searchDto.getCurrentPage()) || searchDto.getCurrentPage()<1){
            searchDto.setCurrentPage(BaseConstant.PAGE_INIT);
        }
        if(Objects.isNull(searchDto.getPageSize()) || searchDto.getPageSize()<=0){
            searchDto.setPageSize(BaseConstant.ARTICLE_PAGE_SIZE);
        }
        Page<ArticleItemVo> page = new Page<>(searchDto.getCurrentPage(), searchDto.getPageSize());
        searchDto.setUserId(ShiroUtils.getAuthUser().getId());
        return Result.ofSuccess(blogArticleService.searchBlogArticleByUserId(page,searchDto));
    }


    @ApiOperation("根据Id查询文字")
    @RequiresAuthentication
    @GetMapping("/getById/{id}")
    public Result getArticleById(@PathVariable(value = "id") Integer id){
        return Result.ofSuccess(blogArticleService.getBlogArticleById(id));

    }

    @ApiOperation("更新文章的阅读量")
    @RequiresAuthentication
    @GetMapping("/updateViewCount/{id}")
    public Result updateViewCount(@PathVariable(value = "id") Integer id){
        BlogArticle exist = blogArticleService.getById(id);
        if(Objects.nonNull(exist)){
            exist.setViewCount(exist.getViewCount()+1);
            blogArticleService.updateById(exist);
            return Result.ofSuccess();
        }
        return Result.ofFail();
    }

    @ApiOperation("获取博客侧边信息")
    @RequiresAuthentication
    @GetMapping("/getBlogAsideInfo")
    public Result getBlogAsideInfo(){
        return Result.ofSuccess(blogArticleService.getBlogAsideInfo(ShiroUtils.getAuthUser().getId()));
    }

}
