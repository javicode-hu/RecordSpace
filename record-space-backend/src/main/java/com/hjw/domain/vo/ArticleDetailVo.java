package com.hjw.domain.vo;

import com.hjw.domain.entity.BlogColumn;
import com.hjw.domain.entity.BlogTag;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hjw
 * @date 2021年10月20日 0:16
 * @description
 */
@Data
public class ArticleDetailVo {

    private Integer id;

    private String author;

    private String title;

    private String content;

    private LocalDateTime createTime;

    private Integer viewCount;

    private Integer likeCount;

    private List<BlogTag> tagList;

    private List<BlogColumn> columnList;
}
