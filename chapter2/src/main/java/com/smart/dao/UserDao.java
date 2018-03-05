package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author:houfy
 * @Description:
 * @Date:Created in 16:18 2018/3/2
 * @Modified By:
 */
@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

//    SQL String
    private final static String SQL_SELECT_COUNT = " SELECT COUNT(*) FROM t_user " +
            " WHERE user_name = ?  and password = ? ";
    private final static String SQL_SELECT_USER = " SELECT * FROM t_user "
            + " WHERE user_name = ? ";
    private final static String SQL_UPDATE_LOGIN_INFO = " UPDATE t_user SET " +
            " last_visit = ?, last_ip = ?, credits = ? WHERE user_id = ? ";

    @Autowired
    private void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     *  根据用户名，密码 查询匹配USER 数量
     *  */
    public int getMatchCount(String userName, String password){
        return(jdbcTemplate.queryForObject(SQL_SELECT_COUNT,new Object[]{userName,password},Integer.class)).intValue();
    }

    public User findUserByName(final String userName){
        final User user = new User();

        jdbcTemplate.query(SQL_SELECT_USER, new Object[]{userName},
//                匿名查询结果回调处理器
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(resultSet.getInt("credits"));
                    }
                }
        );
        return user;
    }

    public void updateLoginInfo(User user){
        jdbcTemplate.update(SQL_UPDATE_LOGIN_INFO
                ,new Object[]{user.getLastVisit(),user.getLastIp(),user.getCredits(),user.getUserId()});
    }



}
