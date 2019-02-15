package com.zoy.springboot.service.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zouzp on 2019/2/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskPoolTest {
    @Autowired
    private TaskPool task;

    @Test
    public void test() throws Exception{
        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();

        Thread.currentThread().join();
    }
}
