package com.socket.config;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据处理
 */
public class DealWithService {

    public DealWithService() {
    }

    public String deal(String readInfo) {
        String result = "我还不是太明白,但我会努力学习，变得越来越好!";
        if (readInfo.contains("你好")) {
            result = "你也好";
        }
        if (readInfo.contains("是谁") || readInfo.contains("叫什么")) {
            result = "我是小爱同学啊";
        }
        if (readInfo.contains("几点") || readInfo.contains("时候") || readInfo.contains("时间")) {
            result = "现在是北京时间：" + timeFormat();
        }
        if(readInfo.contains("笑话")||readInfo.contains("段子")){
            result = "交警拦下我：“你为什么闯红灯？”　　我说：“要是绿灯还用闯么？”　　他竟无言以对。";
        }
        if(readInfo.contains("不")){
            result = "人家尽力了！";
        }
        return result;
    }

    public static String timeFormat() {
        return new SimpleDateFormat("YYYY年MM月dd日 HH点mm分").format(new Date());
    }

}
