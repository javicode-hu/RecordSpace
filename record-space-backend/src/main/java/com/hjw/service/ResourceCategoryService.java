package com.hjw.service;

import com.hjw.domain.entity.ResourceCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjw.domain.vo.CategoryNodeVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjw
 * @since 2021-10-15
 */
public interface ResourceCategoryService extends IService<ResourceCategory> {

    List<ResourceCategory> getCategoryTreeByTypeAndUserId(String type, Integer userId);

    boolean addCategory(ResourceCategory category);
}
