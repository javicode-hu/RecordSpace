package com.hjw.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjw.common.constant.BaseConstant;
import com.hjw.common.exception.BaseException;
import com.hjw.domain.dto.LoginDto;
import com.hjw.domain.entity.SysUser;
import com.hjw.mapper.SysUserMapper;
import com.hjw.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjw
 * @since 2021-10-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Override
    public SysUser checkLoginUser(LoginDto loginDto) {
        SysUser user = getOne(new QueryWrapper<SysUser>().eq("username", loginDto.getUsername()));
        if(Objects.isNull(user)){
            throw new BaseException("用户名不存在");
        }
        if(!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            throw new BaseException("密码错误！");
        }
        if(!user.getStatus().equals(BaseConstant.USER_STATUS_NORMAL)){
            throw new BaseException("用户异常！");
        }
        return user;
    }
}
