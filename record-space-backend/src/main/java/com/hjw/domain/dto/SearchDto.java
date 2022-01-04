package com.hjw.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @author hjw
 * @date 2021年10月20日 22:26
 * @description
 */
@Data
public class SearchDto {


    /**
     * 当前页
     */
    private Integer currentPage;

    /**
     * 当前页数
     */
    private Integer pageSize;

    /**
     * 排序字段 默认创建事件
     */
    private String sortParam = "create_time";

    /**
     * 排序方式 默认降序
     */
    private String sortMethod = "desc";

    /**
     * 关键字
     */
    private String searchKey;


    /**
     * 当前用户id
     */
    private Integer userId;

    /**
     * 所属专栏ID
     */
    private Integer columnId;

    /**
     * 所属标签ID
     */
    private Integer tagId;


    /**
     * 所属分类ID
     */
    private List<Integer> categoryIds;


    /**
     * 资源类型
     */
    private String type;

}
