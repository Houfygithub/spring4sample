package com.smart.service;

import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author:houfy
 * @Description:
 * @Date:Created in 17:44 2018/3/2
 * @Modified By:
 */
@Service
public class UserService {

    private UserDao userDao;

    private LoginLogDao loginLogDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }
    /**
     *  用于检查 用户名和密码的匹配性
     *  */
    public boolean hasMatchUser(String userName, String password){
        int matchCount = userDao.getMatchCount(userName,password);
        return matchCount > 0;
    }

    /**
     * 以用户名为条件 加载 User 对象
     * */
    public User findUserByName(String userName){
        return userDao.findUserByName(userName);
    }

    @Transactional
    public void loginSuccess(User user){
//      更新 登录日志
        LoginLog loginLog = LoginLog.buildLoginLogByUser(user);
        loginLogDao.insertLoginLog(loginLog);

//        更新 用户信息
        user.setCredits(user.getCredits() + 5);
        userDao.updateLoginInfo(user);
    }














}
