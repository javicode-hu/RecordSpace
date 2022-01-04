package com.hjw.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author hjw
 * @date 2021年10月18日 13:46
 * @description 添加员工
 */
@Data
public class ArticleAddDto {

    @ApiModelProperty(value = "标题")
    @NotBlank(message = "标题不能为空")
    private String title;

    @ApiModelProperty(value = "内容")
    @NotBlank(message = "内容不能为空")
    private String content;


    @ApiModelProperty("专栏ID 集合（可空）")
    private List<Integer> columnIdList;

    @ApiModelProperty("标签ID 集合（可空）")
    private List<Integer> tagIdList;


    @ApiModelProperty(value = "创建者")
    private Integer userId;

}
