package com.hjw.common.response;

import lombok.Getter;

/**
* @author hjw
* @date 2021/10/15 13:00
* @description 状态码封装
*/

@Getter
public enum ResultStatus {

    /**
     * 状态码 枚举
     */
    SUCCESS(200,"成功"),

    ERROR(500,"服务器出错了！"),

    ERROR_PARAM(101,"参数异常！"),

    REQUEST_METHOD_ERROR(102,"请求方式错误!"),

    UNAUTHORIZED(401,"用户没有权限!")

    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 内容
     */
    private String message;


    ResultStatus(Integer code , String message){
        this.code = code;
        this.message = message;
    }

}
