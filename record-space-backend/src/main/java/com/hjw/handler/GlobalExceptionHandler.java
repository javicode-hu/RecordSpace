package com.hjw.handler;

import com.hjw.common.exception.BaseException;
import com.hjw.common.response.Result;
import com.hjw.common.response.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hjw
 * @date 2021年10月15日 13:15
 * @description 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {



    @ResponseBody
    @ExceptionHandler(value =Exception.class)
    public Result exceptionHandler(Exception e ) {
        // 参数校验未通过
        if (e instanceof MethodArgumentNotValidException) {
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
            List<String> msgList = fieldErrors.stream().map(FieldError :: getDefaultMessage).collect(Collectors.toList());
            return Result.ofStatus(ResultStatus.ERROR_PARAM,String.join(",", msgList));
        }

        // http 请求方式错误
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return Result.ofStatus(ResultStatus.REQUEST_METHOD_ERROR);
        }

        //自定义异常
        if (e instanceof BaseException){
            return Result.ofException((BaseException) e);
        }

        //shiro异常
        if (e instanceof ShiroException){
            return Result.ofStatus(ResultStatus.UNAUTHORIZED);
        }

        return Result.ofStatus(ResultStatus.ERROR);
    }

}
