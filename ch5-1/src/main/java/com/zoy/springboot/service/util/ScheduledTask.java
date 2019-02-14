package com.zoy.springboot.service.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zouzp on 2019/2/14.
 */
@Component
public class ScheduledTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 1000) // ·每秒执行
    public void printCurrTime() {
        System.out.println("当前时间是 ： " + dateFormat.format(new Date()));
    }
}
