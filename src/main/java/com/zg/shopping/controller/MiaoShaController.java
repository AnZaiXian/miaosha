package com.zg.shopping.controller;


import com.zg.shopping.bean.Email;
import com.zg.shopping.bean.Goods;
import com.zg.shopping.bean.Seckill;
import com.zg.shopping.bean.User;
import com.zg.shopping.service.SeckillService;

import com.zg.shopping.thread.MyRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lenovo on 2017/8/26.
 */
@Controller
@RequestMapping("wrx")
public class MiaoShaController {

    @Autowired
    private SeckillService seckillService;

    //声明 jedis
    final String watchkeys = "watchkeys";
    ExecutorService executor = Executors.newFixedThreadPool(20);  //20个线程池并发数
    final  Jedis jedis = new Jedis("192.168.36.129", 6379);
    /**
     * 马上抢购==>秒杀 id
     */
    @RequestMapping("qgong")
    public String qgong(HttpSession session,Integer id,Map<String,String> map){


        System.out.println("器前台传来的商品id为:"+id);
       //从session缓存中获取用户的名字,如果用户名不为空就给给抢购的数量加1
       /*User user =  (User)session.getAttribute("user");
        String userName = user.getUserName();
        if(userName!=null){
            num++;
        }*/

       String[] arr = {"张三","李四","王五","赵六","田七","懒风","撒币小鬼","惊喜","撒币轩"};
       int  num = arr.length;

        System.out.println("目前有:"+num+"个人抢购");

        //调用多线程+redis做秒杀*******************************************
        //从redis中获取商品id为**的抢购数量
        String number = jedis.get(id.toString());
        jedis.set(watchkeys, number);//设置起始的抢购数
        System.out.println("商品id为"+id+"的商品的抢购数量:"+number);
        jedis.close();

        //设置赶回的页面
        String yem = "";

        //遍历数组
        for (int i = 0; i < arr.length; i++) {//设置1000个人来发起抢购  +user.getUserName()

            System.out.println("用户名:"+arr[i]);
            executor.execute(new MyRunnable("user"+arr[i]));

            //从redis中获取每个用户的抢购情况,抢购成功的返回到商品详情页面
            //indexOf是你判断info字符串中是否包含某个字符或字符
            String info = jedis.get(arr[i]);

            if(info.indexOf("抢购成功")!=-1){

                yem = "success";
            }else if(info.indexOf("抢购失败")!=-1){

                yem = "error";
            }else if(info.indexOf("抢购完毕")!=-1){
                yem = "error";
            }

        }
        executor.shutdown();


        //如果抢购成功的或则进到添加购物车的页面,如果失败,在进到购买页面,并且提示抢购失败
        return yem;
    }

    /**
     * 在redis中设置成功或失败的标记:随机的用户名
     * @param length
     * @return
     */
    /*public static String getRandomString(int length) { //length是随机字符串长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }*/


   /**
     * 当用户点击提醒我的时候将用户的姓名和email,以及他点购的商品的开抢时间存到数据库中
     */
    @RequestMapping("sendEmail")
    public String sendEmail(HttpSession session,int id) throws ParseException {
     //整合时从服务器获取session中的用户名和email*************************************************
       //根据商品的id查询商品的开抢时间
        System.out.println("商品的id为:"+id);
        Seckill seckill = seckillService.selectStarTimeById(id);
        System.out.println(seckill);

        Email email = new Email();
        email.setUname("串串香");
        email.setStarttime(seckill.getStartTime());
        email.setUemail("980708601@qq.com");

        seckillService.addemail(email);
        return  "miaosha";
    }




    /**
     * 查出要进行秒杀的商品
     * @param map
     * @return
     */
    @RequestMapping("miaosha")
    @ResponseBody
    public List<Seckill> toMiaoSha(Map<String,Object> map,String startTime,String endTime) throws ParseException {

        //声明一个向前台返回商品状态的变量
        String info =null;
        int day = 0;

        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = sdf.format(date);
        System.out.println("当前时间为:"+format);

         // 获取秒杀表的秒杀开始时间,去重
        List<Seckill> seckills = seckillService.selectStartTime();
        List<Seckill> slist = new ArrayList<Seckill>();
        for(Seckill s: seckills){
            System.out.println("开始秒杀的时间[去重]"+s.getStartTime()+"结束时间:"+s.getEndTime());
            //判断当前时间和秒杀开始的时间大小,  >秒杀进行中, <要计算出距秒杀开始还有多长时间, =秒杀进行中
            //2017-08-31 18:00:00
            Date parse = sdf.parse(s.getStartTime());//秒杀开始时间
            //计算相差的时间
            if(date.compareTo(parse)>=0){
               info="秒杀进行中";

            }else  if(date.compareTo(parse)<0){
                info="即将开始";

            }

            String  ms = parse.getHours()+":"+parse.getMinutes();

             //System.out.println("描述:"+ms);
            //System.out.println("状态信息:"+info);
            //创建一个秒杀对象
            Seckill seckill = new Seckill();
            seckill.setStartTime(s.getStartTime());//设置开始的时间
            seckill.setEndTime(s.getEndTime());//设置结束时间
            seckill.setDay(day);

            seckill.setInfos(info);//设置商品的状态

            seckill.setMs(ms);//设置商品秒杀页面的表头

            slist.add(seckill);
            System.out.println("*********"+seckill.getInfos()+seckill.getMs()+"===="+seckill.getHour()+":"+seckill.getMins()+":"+seckill.getSecond());
        }

        System.out.println("================跟新集合=====================");
        for(Seckill s: slist){
            //获取商品的 id 以及 抢购量 number 写入redis
           // System.out.println("商品的 id:"+s.getGoods().getGsid()+",抢购量:"+s.getSeckillId()+"");
            //jedis.setnx(s.getGoods().getGsid()+"", s.getSeckillId()+"");

            System.out.println(s);
        }



        return slist;
    }

    /**
     * 根据秒杀的日期来查询秒杀商品的信息
      */
    @RequestMapping("stail")
    @ResponseBody
    public List<Seckill> staill(HttpServletRequest request){
        System.out.println("=========属于我的ajax================");
      String  startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        List<Seckill> seckillsList = seckillService.selectMS(startTime,endTime);
        for (Seckill s:seckillsList) {
            //计算出当前商品剩余量
            int i = s.getGoods().getGoodsCount() - s.getGoods().getGoodsSales();
            Goods goods = s.getGoods();
            goods.setGoodsCount(i);
            s.setGoods(goods);
            System.out.println("目前的库存量为:"+i);
            System.out.println(s);
        }

         return seckillsList;
    }



}
