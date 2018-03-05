package com.smart.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:houfy
 * @Description:
 * @Date:Created in 16:14 2018/3/2
 * @Modified By:
 */
@Data
public class LoginLog implements Serializable {

    private int loginLogId;

    private int userId;

    private String ip;

    private Date loginDate;

    public static LoginLog buildLoginLogByUser(User user){
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        return loginLog;
    }
}
