package com.hjw.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author hjw
 * @date 2021年10月15日 18:43
 * @description
 */
@Data
public class LoginDto {
    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
