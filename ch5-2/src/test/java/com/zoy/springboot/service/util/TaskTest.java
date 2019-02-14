package com.zoy.springboot.service.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Future;

/**
 * Created by zouzp on 2019/2/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAsync
public class TaskTest {

    @Autowired
    private Task task;

    @Test
    public void test() throws Exception{
        long startTime = System.currentTimeMillis();
        Future<String> taskOne = task.doTaskOne();
        Future<String> taskTwo = task.doTaskTwo();
        Future<String> taskThree = task.doTaskThree();
        while (true) {
            if (taskOne.isDone() && taskTwo.isDone() && taskThree.isDone()) {
                break;// ·三个任务都调用完成，退出循环，等待1秒
            }
            Thread.sleep(1000);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总共耗时 ： " + (endTime - startTime));
    }
}
