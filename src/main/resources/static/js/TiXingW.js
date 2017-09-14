/**
 * Created by lenovo on 2017/8/30.
 */


$(document).ready(function(){

    /*var s = "2017-09-02 09:48:55";
    var a = s.split(" ");
    var b = a[0].split("-");
    var c = a[1].split(":");
    var date = new Date(b[0], b[1], b[2], c[0], c[1], c[2])
    //alert(date);

    //alert(date.getHours());*/

    //获取后台传来的集合
     $.ajax({
         type:"POST",
         url:"wrx/miaosha",
         success:function(data){
             //获取当前日期
             var myDate = new Date();
             myDate.toLocaleDateString(); //获取日期与时间

             var json =  JSON.stringify(data); //可以将json对象转换成json对符串
            // var json =  JSON.parse(json2); //可以将json字符串转换成json对象
            //alert("后台传来的集合:"+data+"===json字符串"+json);
             //遍历集合
             var xqo = eval('(' + json + ')');

             var hour;
             var minutes;
             var seconds;
             for(var i in xqo){

                 if(xqo[i].infos=="即将开始"){

                     var s = xqo[i].startTime;
                     var a = s.split(" ");
                     var b = a[0].split("-");
                     var c = a[1].split(":");
                     var d = new Date(b[0], b[1], b[2], c[0], c[1], c[2]);
                     //alert(d);

                     //alert(d.getHours()+":"+ d.getMinutes()+":"+d.getSeconds());


                     if(myDate.getMinutes()>=d.getMinutes()){

                         hour = myDate.getMinutes()-d.getMinutes();

                     }else {
                         hour = (myDate.getMinutes()-d.getMinutes())*(-1);
                     }

                     if(myDate.getMinutes()>=d.getMinutes()){
                         minutes = myDate.getMinutes()-d.getMinutes();
                     }else {
                         minutes = (myDate.getMinutes()-d.getMinutes())*(-1);
                     }

                     if(myDate.getSeconds()>=d.getSeconds()){
                         seconds = myDate.getSeconds()-d.getSeconds();
                     }else {
                         seconds = (myDate.getSeconds()-d.getSeconds())*(-1);
                     }

                     //alert(xqo[i].hour+":"+xqo[i].mins+":"+xqo[i].second);
                    // alert("即将开始的时间还有:"+hour+":"+minutes+":"+seconds);//xqo[i].startTime
                     $("#timeOder").append(
                     "<li id='liTime'  data-begin='"+myDate+"' data-end='"+xqo[i].endTime+"' data-plate-id='EMP201708310083511' data-index='now'>"+
                     "<div class='rob-list js-hui-tag'>"+
                     "<span>"+
                     "<em></em>"+
                     "<b>"+d.getHours()+":"+d.getMinutes()+"</b>"+
                     "<em>即将开始</em>"+
                     "</span>"+
                     "</div>"+
                     "<div class='limited-date js-time '>"+
                     " <div class='inner'>"+
                     "<p>距离本场开始还有</p>"+
                     "<div class='ending'>"+
                     "<span class='time'><em></em><span role='timer' data-type='hour' id='hour'>"+hour+"</span></span>：<span class='time'><em></em><span role='timer' data-type='min' id='min'>"+minutes+"</span></span>：<span class='time'><em></em><span role='timer' data-type='sec' id='seconds'>"+seconds+"</span></span>"+
                     "</div>"+
                     "<div class='date-icon'>"+
                     "<i class='i-adorn yellow1-cicle'></i>"+
                     "<i class='i-adorn blue-cicle'></i>"+
                     "<i class='i-adorn yellow2-cicle'></i>"+
                     "<i class='i-adorn lightbe1-cicle'></i>"+
                     "<i class='i-adorn pink-cicle'></i>"+
                     "<i class='i-adorn yellow3-cicle'></i>"+
                     "<i class='i-strip pink1-strip'></i>"+
                     "<i class='i-strip cyan1-strip'></i>"+
                     "<i class='i-strip yellow1-strip'></i>"+
                     "<i class='i-strip cyan2-strip'></i>"+
                     "<i class='i-strip blue1-strip'></i>"+
                     "</div>"+
                     "</div>"+
                     "</div>"+
                     "</li>"
                 );



                 }else if(xqo[i].infos=="秒杀进行中"){


                     //禁用提醒我的按钮
                     $("#butn").attr("disabled");

                     var s = xqo[i].endTime;
                     var a = s.split(" ");
                     var b = a[0].split("-");
                     var c = a[1].split(":");
                     var d = new Date(b[0], b[1], b[2], c[0], c[1], c[2])
                     //alert(d);


                     var s2 = xqo[i].startTime;
                     var a2 = s2.split(" ");
                     var b2 = a2[0].split("-");
                     var c2 = a2[1].split(":");
                     var d2 = new Date(b2[0], b2[1], b2[2], c2[0], c2[1], c2[2])


                     //alert(d.getHours()+":"+ d.getMinutes()+":"+d.getSeconds());

                     if(myDate.getMinutes()>=d.getMinutes()){

                         hour = myDate.getMinutes()-d.getMinutes();

                     }else {
                         hour = (myDate.getMinutes()-d.getMinutes())*(-1);
                     }

                     if(myDate.getMinutes()>=d.getMinutes()){
                         minutes = myDate.getMinutes()-d.getMinutes();
                     }else {
                         minutes = (myDate.getMinutes()-d.getMinutes())*(-1);
                     }

                     if(myDate.getSeconds()>=d.getSeconds()){
                         seconds = myDate.getSeconds()-d.getSeconds();
                     }else {
                         seconds = (myDate.getSeconds()-d.getSeconds())*(-1);
                     }


                     //alert("秒杀进行中结束的时间:"+hour+":"+minutes+":"+seconds);

                     $("#timeOder").append(
                         "<li class='on' id='liTime' data-begin='"+myDate+"' data-end='"+xqo[i].endTime+"' data-plate-id='EMP201708310083511' data-index='now'>"+
                         "<div class='rob-list js-hui-tag'>"+
                         "<span>"+
                         "<em></em>"+
                         "<b>"+d2.getHours()+":"+d2.getMinutes()+"</b>"+
                         "<em>抢购中</em>"+
                         "</span>"+
                         "</div>"+
                         "<div class='limited-date js-time '>"+
                         " <div class='inner'>"+
                         "<p>距离本场结束还有</p>"+
                         "<div class='ending'>"+
                         "<span class='time'><em></em><span role='timer' data-type='hour' id='hour'>"+hour+"</span></span>：<span class='time'><em></em><span role='timer' data-type='min' id='min'>"+minutes+"</span></span>：<span class='time'><em></em><span role='timer' data-type='sec' id='seconds'>"+seconds+"</span></span>"+
                         "</div>"+
                         " <div class='date-icon'>"+
                         "<i class='i-adorn yellow1-cicle'></i>"+
                         "<i class='i-adorn blue-cicle'></i>"+
                         "<i class='i-adorn yellow2-cicle'></i>"+
                         "<i class='i-adorn lightbe1-cicle'></i>"+
                         "<i class='i-adorn pink-cicle'></i>"+
                         "<i class='i-adorn yellow3-cicle'></i>"+
                         "<i class='i-strip pink1-strip'></i>"+
                         "<i class='i-strip cyan1-strip'></i>"+
                         "<i class='i-strip yellow1-strip'></i>"+
                         "<i class='i-strip cyan2-strip'></i>"+
                         "<i class='i-strip blue1-strip'></i>"+
                         "</div>"+
                         "</div>"+
                         "</div>"+
                         "</li>"
                     );

                 }

                //window.location="wrx/stail?startTime="+xqo[i].startTime+"&endTime="+xqo[i].endTime;
                 //根据秒杀的时间查询以商品的详细信息
                 //enter.ashx？name="+username+"&password="+pass;
                $.ajax({
                     type:"POST",
                     url:"wrx/stail?startTime="+xqo[i].startTime+"&endTime="+xqo[i].endTime,
                     success:function(data){

                         var json =  JSON.stringify(data); //可以将json对象转换成json对符串
                         //遍历集合==将json对象数组转换成对象数组
                         var datil = eval('(' + json + ')');
                        // //alert(json+"========="+datil);
                         for(var j in datil){

                         if(j==0){

                             $("#sku_list_content").append(
                                 "<li class='js-sku'> " +
                                 "<div class='list-img fn-left'> <img src='"+datil[j].goods.goodsImg+"' class='js-lazyload' data-original='' style='display: inline;' width='180' alt=''/>" +
                                 "<em class='em-move'></em></div><div class='list-con'> <div class='title'> <h4><span>"+datil[j].goods.goodsname+"</span></h4>" +
                                 " <p class='sub-head'></p> </div> <div class='purchase'> <p><span class='fn-rmb'>¥</span><em><span>"+datil[j].goods.goodsMoney+"</span></em><del>¥<span>"+datil[j].goods.goodsMoney*1.5+"</span></del></p>" +
                                 "</div> <div class='payment'> <span class='gz'>仅限<em><span>"+datil[j].number+"</span></em>件</span> </div> " +
                                 "<div class='purchase'>" +"<input type='hidden'' id='id' value='"+datil[j].seckillId+"'>"+
                                 "<span class='pur grab'><button class='pur grab' onclick='qg()'>去抢购</button></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                 "<span class='pur grab'><button class='pur grab' id='butn' onclick='tx()'>提醒我</button></span>"
                             );

                         } else  if(j%2==0){
                             $("#sku_list_content").append(
                                 "<li class='js-sku'> " +
                                 "<div class='list-img fn-left'> <img src='"+datil[j].goods.goodsImg+"' class='js-lazyload' data-original='' style='display: inline;' width='180' alt=''/>" +
                                 "<em class='em-move'></em></div><div class='list-con'> <div class='title'> <h4><span>"+datil[j].goods.goodsname+"</span></h4>" +
                                 " <p class='sub-head'></p> </div> <div class='purchase'> <p><span class='fn-rmb'>¥</span><em><span>"+datil[j].goods.goodsMoney+"</span></em><del>¥<span>"+datil[j].goods.goodsMoney*1.5+"</span></del></p>" +
                                 "</div> <div class='payment'> <span class='gz'>仅限<em><span>"+datil[j].number+"</span></em>件</span> </div> " +
                                 "<div class='purchase'>" +"<div class='purchase'>" +"<input type='hidden'' id='id' value='"+datil[j].seckillId+"'>"+
                                 "<span class='pur grab'><button class='pur grab' onclick='qg()'>去抢购</button></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                 "<span class='pur grab'><button class='pur grab' id='butn' onclick='tx()'>提醒我</button></span>"
                             );

                         }else {

                             $("#sku_list_content").append(
                                 "<li class='js-sku'> " +
                                 "<div class='list-img fn-left'> <img src='"+datil[j].goods.goodsImg+"' class='js-lazyload' data-original='' style='display: inline;' width='180' alt=''/>" +
                                 "<em class='em-move'></em></div><div class='list-con'> <div class='title'> <h4><span>"+datil[j].goods.goodsname+"</span></h4>" +
                                 " <p class='sub-head'></p> </div> <div class='purchase'> <p><span class='fn-rmb'>¥</span><em><span>"+datil[j].goods.goodsMoney+"</span></em><del>¥<span>"+datil[j].goods.goodsMoney*1.5+"</span></del></p>" +
                                 "</div> <div class='payment'> <span class='gz'>仅限<em><span>"+datil[j].number+"</span></em>件</span> </div> " +
                                 "<div class='purchase'>" +"<input type='hidden'' id='id' value='"+i+"'>"+
                                 "<span class='pur grab'><button class='pur grab' onclick='qg()'>去抢购</button></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                                 "<span class='pur grab'><button class='pur grab' onclick='tx()'>提醒我</button></span>"
                             );
                         }


                         }
                }

                 });


             }




         }
     });

});

/*遍历json对象数组
 var str = '[{"name":"宗2瓜","num":"1","price":"122"},{"name":"宗呱呱","num":"1","price":"100"}]';
 var xqo = eval('(' + str + ')');
 for(var i in xqo){
 　　//alert(xqo[i].name);
 }
 */

/**
 * 马上抢购
 */
function qg(){
    alert("马上抢购");
    //attr获取商品的id
    var id = $("#id").attr("value");
    alert(id);
    $.ajax({
        type:"GET",
        dataType:"json",
        data:{"id":id },
        url:"wrx/qgong",
        success:function(data){
            alert(data);
        }

    });
}

function tx(){
    alert("=====tx方法=====");
    //获取开始抢购的时间=====>将日期类型转换成时间类型==Thu Mar 19 2015 12:00:00 GMT+0800 ==== 2015-3-19 12:00:00
    //attr获取商品的id
    var id = $("#id").attr("value");

    alert(id);
    //通过ajax将开始的时间传到后台,计算出当前时间与开始的时间差,然后在相应的时间差后UI该客户发送email
    $.ajax({
        type:"GET",
        dataType: "json",
        data:{ "id": id },
        url:"wrx/sendEmail",
        success:function(data){
          alert(data);
        }

    });


}


//将date型转换为tring

//传来的datetime是:Wed Mar 04 2009 11:05:05 GMT+0800格式  得到结果：2009-06-12 17:18:05

function dateToStr(datetime){
    alert(datetime);
    var year = datetime.getFullYear();

    var month = datetime.getMonth()+1;//js从0开始取
    var date = datetime.getDate();
    var hour = datetime.getHours();
    var minutes = datetime.getMinutes();
    var second = datetime.getSeconds();

    //alert(year+"-"+month+"-"+date+" "+hour+":"+minutes+":"+second);
    if(month<10){
        month = "0" + month;
    }
    if(date<10){
        date = "0" + date;
    }
    if(hour <10){
        hour = "0" + hour;
    }
    if(minutes <10){
        minutes = "0" + minutes;
    }
    if(second <10){
        second = "0" + second ;
    }

    var time = year+"-"+month+"-"+date+" "+hour+":"+minutes+":"+second; //2009-06-12 17:18:05
     alert(time);
    return time;
}


