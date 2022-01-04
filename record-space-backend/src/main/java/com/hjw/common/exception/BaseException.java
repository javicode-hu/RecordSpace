package com.hjw.common.exception;

import com.hjw.common.response.Result;
import com.hjw.common.response.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * @author hjw
 * @date 2021年10月15日 13:09
 * @description 异常基类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException{

    private static final long serialVersionUID = 3506744187536228284L;

    private Integer code;

    private String message;

    public BaseException(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public BaseException(String message){
        this.code = ResultStatus.ERROR.getCode();
        this.message = message;
    }

    public BaseException(HttpStatus httpStatus){
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }

}
