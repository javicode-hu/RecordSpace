package com.hjw.shiro;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjw.common.constant.BaseConstant;
import com.hjw.domain.entity.SysUser;
import com.hjw.service.SysUserService;
import com.hjw.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author hjw
 * @date 2021年10月15日 15:59
 * @description
 */
@Slf4j
@Component
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    SysUserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof  JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String jwtToken = (String) authenticationToken.getCredentials();

        Long userId = jwtUtils.getUserId(jwtToken);
        if(Objects.isNull(userId)){
            throw new AuthenticationException("登录信息已过期!");
        }
        SysUser user = userService.getById(userId);
        if(Objects.isNull(user)){
            throw new AuthenticationException("账号不存在!");
        }
        if(user.getStatus().equals(BaseConstant.USER_STATUS_DISABLE)){
            throw new AuthenticationException("账号被锁定!");
        }
        return new SimpleAuthenticationInfo(user, jwtToken,getName());
    }
}
