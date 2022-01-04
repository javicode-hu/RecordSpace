package com.hjw.domain.vo;

import lombok.Data;

/**
 * @author hjw
 * @date 2021年10月28日 15:57
 * @description
 */
@Data
public class WebItemVo {

    private Integer id;

    private String name;

    private String cover;

    private String description;

    private String url;

    private Integer visitCount;

    private Integer likeCount;

}
