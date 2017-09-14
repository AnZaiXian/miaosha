package com.zg.shopping.bean;

import lombok.Data;
import org.codehaus.groovy.runtime.StringGroovyMethods;


/**
 * Created by lenovo on 2017/8/25.
 * 秒杀列表
 */
@Data
public class Seckill {
    private Integer seckillId;//秒杀商品的主键
    private Integer number;//秒杀商品的数量
    private  String startTime;//秒杀的开始时间
    private  String endTime;//结束时间
    private  String createTime;//创建秒杀商品的当前时间
    //商品的主键
    private  Goods goods;

    private int day;//相差的天数
    private int hour;
    private int mins;//相差的分钟
    private int second;//相差的秒数
    private String infos;//商品秒杀的状态
    private String ms;//表头的描述


}
