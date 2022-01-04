package com.hjw.controller;

import com.hjw.common.constant.BaseConstant;
import com.hjw.common.response.Result;
import com.hjw.domain.dto.LoginDto;
import com.hjw.domain.entity.SysUser;
import com.hjw.domain.vo.LoginVo;
import com.hjw.service.SysUserService;
import com.hjw.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author hjw
 * @date 2021年10月15日 12:48
 * @description
 */
@RestController
@Api(value = "登录接口")
public class LoginController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    SysUserService userService;


    /**
     * 登陆
     * @param loginDto
     * @param response
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        SysUser user = userService.checkLoginUser(loginDto);
        if( !Objects.isNull(user)){
            String jwt = jwtUtils.generateToken(user.getId());
            response.setHeader(BaseConstant.ACCESS_TOKEN, jwt);
            response.setHeader("Access-Control-Expose-Headers", BaseConstant.ACCESS_TOKEN);
            LoginVo loginVo = new LoginVo();
            BeanUtils.copyProperties(user,loginVo);
            return Result.ofSuccess(loginVo);
        }
        return Result.ofFail();
    }

    /**
     * 登出
     * @return
     */
    @GetMapping("/logout")
    @RequiresAuthentication
    @ApiOperation("用户登出")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.ofSuccess();
    }
}
