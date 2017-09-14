package com.zg.shopping.bean;

import lombok.Data;

import java.util.Date;

/**
 * Created by lenovo on 2017/8/22.
 * 用户表（t_User）
 */
@Data
public class User {
    private Integer userId;
    private String userName;
    private String userPwd;
    private String phoneNumber;//手机号
    private String headPicture;//用户头像
    private String paymentpwd;//付款密码
    private double balance;//账户余额
    private String nickname;//昵称
    private String gender;//性别
    private Date birthday;//生日
    private String email;//电子邮箱




}
