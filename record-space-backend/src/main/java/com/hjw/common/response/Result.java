package com.hjw.common.response;

import com.hjw.common.exception.BaseException;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Objects;

/**
 * @author hjw
 * @date 2021年10月15日 13:02
 * @description 通用的 API 接口封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Object data;

    public static Result of(Integer code,String message,Object data){
        return new Result(code,message,data);
    }

    public static Result ofStatus(ResultStatus status){return new Result(status.getCode(),status.getMessage(),null); }

    public static Result ofStatus(ResultStatus status, Object data){ return new Result(status.getCode(),status.getMessage(),data); }

    public static Result ofSuccess(){
        return ofStatus(ResultStatus.SUCCESS,null);
    }

    public static Result ofSuccess(Object data){
        return ofStatus(ResultStatus.SUCCESS,data);
    }

    public static Result ofSuccess(String message,Object data){ return of(ResultStatus.SUCCESS.getCode(),message,data); }

    public static Result ofFail(){
        return ofStatus(ResultStatus.ERROR,null);
    }

    public static Result ofFail(String message){
        return of(ResultStatus.ERROR.getCode(),message,null);
    }

    public static Result ofFail(String message,Object data){
        return of(ResultStatus.ERROR.getCode(),message,data);
    }


    public static  Result ofException(BaseException e,Object data){
        return of(e.getCode(),e.getMessage(),data);
    }

    public static Result ofException(BaseException e){
        return ofException(e,null);
    }

    public static Result ofHttpStatus(HttpStatus httpStatus, Object data){
        return of(httpStatus.value(),httpStatus.getReasonPhrase(),data);
    }

    public static Result ofHttpStatus(HttpStatus httpStatus){
        return  ofHttpStatus(httpStatus,null);
    }

}
