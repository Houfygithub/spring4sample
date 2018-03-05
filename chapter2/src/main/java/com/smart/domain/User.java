package com.smart.domain;




import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:houfy
 * @Description:
 * @Date:Created in 15:48 2018/3/2
 * @Modified By:
 */
@Data
public class User implements Serializable{
    private int userId;

    /**
     * 身份信息
     * */
    private String userName;
    private String password;

    /**
     * 积分
     * */
    private int credits;

    /**
     * 最后一次登录信息
     * */
    private String lastIp;
    private Date lastVisit;
}
