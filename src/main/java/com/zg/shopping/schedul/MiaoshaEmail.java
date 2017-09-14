package com.zg.shopping.schedul;

import com.zg.shopping.bean.Email;
import com.zg.shopping.service.MailService;
import com.zg.shopping.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by lenovo on 2017/9/4.
 */
@Component
public class MiaoshaEmail {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 发送邮件
     */
    @Autowired
    private MailService mailService;

    /**
     * 查询提醒我的记录表
     */
    @Autowired
    private SeckillService seckillService;



    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }

    private int count=0;

    /**
     * 2017-09-04 08:00:00
     */
    @Scheduled(cron="0 0 8 * * ?")
    private void process(){
        /**
         * 查询提醒我表信息
         */
        List<Email> elist = seckillService.selectEmail();
        if(elist.size()>0&&elist!=null){
            for (Email e: elist){
                if(e.getStarttime().equals("2017-09-04 08:00:00.0")){
                    mailService.sendSimpleMail(e.getUemail(),"尊敬的"+e.getUname()+"用户", "你订购的商品到时间抢购了");
                }
            }
        }


        System.out.println("2017-09-04 08:00:00===this is scheduler task runing  "+(count++) + "  " +  new Date());
    }


    /**
     * 2017-09-04 23:10:00
     */
    @Scheduled(cron="0 10 23 * * ?")
    private void process3(){
    /**
     * 查询提醒我表信息
     */
        List<Email> elist = seckillService.selectEmail();
        if(elist.size()>0&&elist!=null){
            for (Email e: elist){
                if(e.getStarttime().equals("2017-09-04 23:10:00.0")){
                    mailService.sendSimpleMail(e.getUemail(),"尊敬的"+e.getUname()+"用户", "你订购的商品到时间抢购了");
                }
            }
        }
    System.out.println("2017-09-04 23:10:00===this is scheduler task runing  "+(count++) + "  " +  new Date());
    }


    /**
     * 2017-09-04 15:12:10
     */
    @Scheduled(cron="0 12 20 * * ?")
    private void process4(){
        /**
         * 查询提醒我表信息
         */
        List<Email> elist = seckillService.selectEmail();
        if(elist.size()>0&&elist!=null){
            for (Email e: elist){
                System.out.println(e);
                if(e.getStarttime().equals("2017-09-04 15:12:10.0")){
                    mailService.sendSimpleMail(e.getUemail(),"尊敬的"+e.getUname()+"用户", "你订购的商品到时间抢购了");
                }


            }
        }
        System.out.println("2017-09-04 15:12:10===this is scheduler task runing  "+(count++) + "  " +  new Date());
    }

}
