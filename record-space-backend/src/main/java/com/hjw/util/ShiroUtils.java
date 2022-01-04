package com.hjw.util;

import com.hjw.domain.entity.SysUser;
import org.apache.shiro.SecurityUtils;


/**
 * @author hjw
 * @date 2021年10月17日 16:14
 * @description shiro工具类
 */
public class ShiroUtils {

    public static SysUser getAuthUser(){
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

}
