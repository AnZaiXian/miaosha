package com.zg.shopping.serviceImpl;

import com.zg.shopping.bean.Email;
import com.zg.shopping.bean.Seckill;
import com.zg.shopping.mapper.SeckillMapper;
import com.zg.shopping.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/8/27.
 */
@Service
public class SeckillServiceImpl implements SeckillService{

    @Autowired
    private SeckillMapper seckillMapper;

    /**
     * 查询秒杀表的秒杀开始时间
     */
    @Override
    public List<Seckill> selectStartTime() {

        return seckillMapper.selectStartTime();
    }

    /**
     * 两表联查,查出要进行秒杀的商品
     */
    @Override
    public List<Seckill> selectMS(String startTime,String endTime) {
        System.out.println("==================");
        List<Seckill> seckills = seckillMapper.selectMS(startTime,endTime);
        return seckills;
    }
    /**
     * 根据商品的id查询商品的开始抢购的时间
     */
    @Override
    public Seckill selectStarTimeById(int id) {
        return seckillMapper.selectStarTimeById(id);
    }
    /**
     * 添加email的信息
     */
    @Override
    public void addemail(Email email) {
        seckillMapper.addemail(email);
    }
    /**
     * 查询提醒我表信息
     */
    @Override
    public List<Email> selectEmail() {
        return seckillMapper.selectEmail();
    }
}
