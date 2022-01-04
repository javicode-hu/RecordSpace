package com.hjw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjw.common.exception.BaseException;
import com.hjw.domain.dto.SearchDto;
import com.hjw.domain.entity.BlogColumn;
import com.hjw.domain.vo.ColumnItemVo;
import com.hjw.mapper.BlogColumnMapper;
import com.hjw.service.BlogColumnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
public class BlogColumnServiceImpl extends ServiceImpl<BlogColumnMapper, BlogColumn> implements BlogColumnService {


    @Autowired
    BlogColumnMapper blogColumnMapper;

    @Override
    public boolean addBlogColumn(BlogColumn blogColumn) {
        BlogColumn existColumn = getOne(new LambdaQueryWrapper<BlogColumn>()
                .eq(BlogColumn::getName, blogColumn.getName())
                .eq(BlogColumn::getUserId, blogColumn.getUserId()),false);
        if(Objects.nonNull(existColumn)){
            throw new BaseException("专栏名称已经被使用啦");
        }
       return save(blogColumn);
    }

    @Override
    public List<BlogColumn> listBlogColumnByUserId(Integer userId) {
        return list(new LambdaQueryWrapper<BlogColumn>().eq(BlogColumn::getUserId, userId));
    }

    @Override
    public IPage<ColumnItemVo> searchBlogColumnByUserId(Page<ColumnItemVo> page, SearchDto searchDto) {
        return blogColumnMapper.searchBlogColumnByUserId(page,searchDto);
    }

    @Override
    public ColumnItemVo getBlogColumnById(Integer id) {
        return blogColumnMapper.getBlogColumnById(id);
    }
}
