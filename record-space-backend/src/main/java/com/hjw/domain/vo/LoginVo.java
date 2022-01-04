package com.hjw.domain.vo;

import lombok.Data;

/**
 * @author hjw
 * @date 2021年10月16日 0:33
 * @description
 */

@Data
public class LoginVo {

    private Integer id;

    private String username;

    private String nickname;

    private String avatar;

    private String email;

    private String phone;


}
