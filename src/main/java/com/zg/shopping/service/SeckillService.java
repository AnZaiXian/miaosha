package com.zg.shopping.service;

import com.zg.shopping.bean.Email;
import com.zg.shopping.bean.Seckill;

import java.util.List;

/**
 * Created by lenovo on 2017/8/27.
 */
public interface SeckillService {

    /**
     * 查询秒杀表的秒杀开始时间
     */
    public List<Seckill> selectStartTime();

    /**
     * 两表联查,查出要进行秒杀的商品
     */
    public List<Seckill> selectMS(String startTime,String endTime);

    /**
     * 根据商品的id查询商品的开始抢购的时间
     */
    public Seckill selectStarTimeById(int id);

    /**
     * 添加email的信息
     */
    public void addemail(Email email);

    /**
     * 查询提醒我表信息
     */
    public List<Email> selectEmail();

}
