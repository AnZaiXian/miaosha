package com.zg.shopping.bean;

import lombok.Data;

/**
 * Created by lenovo on 2017/8/22.
 * 商品表（t_goods）
 */
@Data
public class Goods {
    private Integer gsid;//商品表主键
    private String goodsname;//商品名称
    private double goodsMoney;//商品价格
    private String goodsImg;//商品照片路径goodsImg
    private String goodsAddress;//商品发货地址
    private int goodsCount;//商品的总数量
    private int goodsSales;//商品的累计销量
    private double goodsCarriage;//商品运费

}
