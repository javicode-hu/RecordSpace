package com.hjw;

import com.hjw.domain.entity.SysUser;
import com.hjw.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RecordSpaceBackendApplicationTests {

    @Autowired
    SysUserService userService;

    @Test
    void contextLoads() {

        List<SysUser> list = userService.list();
        System.out.println(list);
    }

}
