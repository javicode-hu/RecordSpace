package com.hjw.service;

import com.hjw.domain.dto.LoginDto;
import com.hjw.domain.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjw
 * @since 2021-10-15
 */
public interface SysUserService extends IService<SysUser> {

    SysUser checkLoginUser(LoginDto loginDto);
}
