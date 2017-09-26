package com.spring.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dalp on 2017/8/14.
 * 定时任务：每隔五秒输出时间
 */
@Component
public class ScheduledTasks {

    //@Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
//    @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
//    @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
    @Scheduled(cron="0 0/30 * * * *") // ：通过cron表达式定义规则
//    @Scheduled(fixedRate = 5000) //该注解定义每隔5秒执行的操作
    public void reportCurrentTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("time: " + dateFormat.format(new Date()));
    }
}

/**
 * Result:
 *time: 15:56:41
 time: 15:56:46
 time: 15:56:51
 time: 15:56:56
 time: 15:57:01
 time: 15:57:06
 */
