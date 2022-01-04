package com.hjw.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hjw
 * @date 2021年10月22日 22:33
 * @description
 */
@Data
public class ColumnItemVo {

    private Integer id;

    private String author;

    private String name;

    private String description;

    private LocalDateTime createTime;

    /**
     * 文章数
     */
    private Integer articleCount;

    /**
     * 阅读量
     */
    private Integer viewCount;

    /**
     * 点赞数
     */
    private Integer likeCount;
}
