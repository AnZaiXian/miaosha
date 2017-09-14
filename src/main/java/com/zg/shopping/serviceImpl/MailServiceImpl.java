package com.zg.shopping.serviceImpl;

import com.zg.shopping.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


/**
 * Created by lenovo on 2017/9/3.
 */
@Component
public class MailServiceImpl implements MailService {
    //定义日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //设置邮件发送者的对象
    @Autowired
    private JavaMailSender mailSender;
    //发送的地址
    @Value("${mail.fromMail.addr}")
    private String from;


    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try {
            mailSender.send(message);
            logger.info

                    ("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }


    }
}
