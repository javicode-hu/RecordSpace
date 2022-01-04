package com.hjw.domain.vo;

import com.hjw.domain.entity.ResourceCategory;
import lombok.Data;

import java.util.List;

/**
 * @author hjw
 * @date 2021年10月28日 10:02
 * @description
 */


@Data
public class CategoryNodeVo {

    private ResourceCategory category;

    private List<CategoryNodeVo> children;

}
