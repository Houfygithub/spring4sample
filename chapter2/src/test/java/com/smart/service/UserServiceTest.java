package com.smart.service;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.*;

/**
 * @Author:houfy
 * @Description:
 * @Date:Created in 10:52 2018/3/5
 * @Modified By:
 */
@ContextConfiguration("classpath*:/smart-context.xml")  // 指定Spring配置文件，启动Spring容器
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {  //继承Spring测试框架基类，启动测试运行器
    private UserService userService;

//    注入Spring容器中的Bean
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }


    @Test  //标注为测试方法
    public void testHasMatchUser() throws Exception {
        boolean b1 = userService.hasMatchUser("admin","123456");
        boolean b2 = userService.hasMatchUser("admin","111");
        assertTrue(b1);
        assertTrue(!b2);
    }

    @Test
    public void testFindUserByName() throws Exception {
        User user = userService.findUserByName("admin");
        assertEquals(user.getUserName(),"admin");
    }

    @Test
    public void testLoginSuccess(){
        User user = userService.findUserByName("admin");
        user.setLastIp("127.0.0.1");
        user.setLastVisit(new Date());
        user.setCredits(5);

        userService.loginSuccess(user);
    }

}