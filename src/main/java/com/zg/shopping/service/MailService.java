package com.zg.shopping.service;

/**
 * Created by lenovo on 2017/9/3.
 * 发送邮件
 */
public interface MailService {

    public void sendSimpleMail(String to, String subject, String content);


}
