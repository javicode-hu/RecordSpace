package com.hjw.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hjw
 * @date 2021年10月25日 22:23
 * @description
 */
@Data
public class TagItemVo {

    private Integer id;

    private String author;

    private String name;

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
