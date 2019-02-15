package com.zoy.springboot.service.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by zouzp on 2019/2/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAsync
public class TaskTest {

    private static final Logger logger = LoggerFactory.getLogger(TaskTest.class);

    @Autowired
    private Task task;

    /**
     * ·测试 任务之间的同步、异步
     * @throws Exception
     */
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

    /**
     * ·测试Future
     * @throws Exception
     */
    @Test
    public void testFuture() throws Exception {
        Future<String> taskOneFuture = task.doTaskOne();
        String result = taskOneFuture.get(5, TimeUnit.SECONDS);
        logger.info(result);
    }
}
