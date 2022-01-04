package com.hjw.domain.vo;

import com.hjw.domain.entity.BlogArticle;
import com.hjw.domain.entity.BlogColumn;
import lombok.Builder;
import lombok.Data;

/**
 * @author hjw
 * @date 2021年10月26日 18:41
 * @description
 */
@Data
@Builder
public class BlogAsideVo {

    private Integer articleCount;

    private Integer columnCount;

    private Integer tagCount;

    private BlogArticle recentArticle;

    private BlogColumn recentColumn;
}
