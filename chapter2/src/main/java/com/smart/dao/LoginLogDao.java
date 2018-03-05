package com.smart.dao;

import com.smart.domain.LoginLog;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Author:houfy
 * @Description:
 * @Date:Created in 17:05 2018/3/2
 * @Modified By:
 */
@Repository
public class LoginLogDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String SQL_UPDATE_LOGIN_LOG = " INSERT INTO t_login_log (user_id,ip,login_datetime) "
            + " VALUES (?,?,?)";

    public void insertLoginLog(LoginLog loginLog){
        jdbcTemplate.update(SQL_UPDATE_LOGIN_LOG,new Object[]{loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate()});
    }

}
