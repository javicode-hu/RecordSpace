package com.hjw.domain.vo;

import com.hjw.domain.entity.BlogTag;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hjw
 * @date 2021年10月18日 18:04
 * @description
 */
@Data
public class ArticleItemVo {

    private Integer id;

    private String author;

    private String title;

    private String content;

    private LocalDateTime createTime;

    private Integer viewCount;

    private Integer likeCount;

    private List<BlogTag> tagList;
}
