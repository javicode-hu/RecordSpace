package com.hjw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hjw.common.exception.BaseException;
import com.hjw.domain.entity.ResourceCategory;
import com.hjw.domain.vo.CategoryNodeVo;
import com.hjw.mapper.ResourceCategoryMapper;
import com.hjw.service.ResourceCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hjw
 * @since 2021-10-15
 */
@Service
public class ResourceCategoryServiceImpl extends ServiceImpl<ResourceCategoryMapper, ResourceCategory> implements ResourceCategoryService {

    /**
     * 返回某类型的目录树
     * @param type
     * @param userId
     * @return
     */
    @Override
    public List<ResourceCategory> getCategoryTreeByTypeAndUserId(String type, Integer userId) {
        List<ResourceCategory> list = list(new LambdaQueryWrapper<ResourceCategory>()
                .eq(ResourceCategory::getType, type)
                .eq(ResourceCategory::getUserId, userId));
        return buildCategoryTree(list);
    }

    @Override
    public boolean addCategory(ResourceCategory category) {
        ResourceCategory exist = getOne(new LambdaQueryWrapper<ResourceCategory>()
                .eq(ResourceCategory::getName, category.getName())
                .eq(ResourceCategory::getUserId, category.getUserId())
                .eq(ResourceCategory::getType,category.getType()));
        if(Objects.nonNull(exist)){
            throw new BaseException("类目名称已经存在");
        }
        return save(category);
    }


    /**
     * 构建目录树
     * @param list
     * @return
     */
    public List<ResourceCategory> buildCategoryTree(List<ResourceCategory> list) {

        //根节点
        List<ResourceCategory> root = new ArrayList<>();

        for(ResourceCategory category:list){
            if (category.getParentId()==0){
                root.add(category);
            }
        }

        //为根菜单设置子菜单，getChild是递归调用的
        for (ResourceCategory category:root){
            /* 获取根节点下的所有子节点 使用getChild方法*/
            List<ResourceCategory> child = getChild(category.getId(), list);
            //给根节点设置子节点
            category.setChildren(child);
        }

        return root;
    }

    /**
     * 获取子节点
     * @param id 父节点id
     * @param list 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    public List<ResourceCategory> getChild(Integer id,List<ResourceCategory> list){
        //子菜单
        List<ResourceCategory> childList  = new ArrayList<>();

        for(ResourceCategory category:list){
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if(category.getParentId().equals(id)){
                childList.add(category);
            }
        }

        //递归
        for(ResourceCategory category:childList){
            category.setChildren(getChild(category.getId(),list));
        }

        //如果节点下没有子节点，返回一个空List（递归退出）
        if(childList.isEmpty()){
            return new ArrayList<>();
        }
        return childList;
    }
}
