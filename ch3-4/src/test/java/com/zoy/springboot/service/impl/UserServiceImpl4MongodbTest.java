package com.zoy.springboot.service.impl;

import com.zoy.springboot.service.IUserService4Mongodb;
import com.zoy.springboot.service.model.User4Mongodb;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/*
·MongoDB简介
MongoDB是一个基于分布式文件存储的数据库，它是一个介于关系数据库和非关系数据库之间的产品，其主要目标是在键/值存储方式（提供了高性能和高度伸缩性）和传统的RDBMS系统（具有丰富的功能）之间架起一座桥梁，它集两者的优势于一身。

MongoDB支持的数据结构非常松散，是类似json的bson格式，因此可以存储比较复杂的数据类型，也因为他的存储格式也使得它所存储的数据在Nodejs程序应用中使用非常流畅。

既然称为NoSQL数据库，Mongo的查询语言非常强大，其语法有点类似于面向对象的查询语言，几乎可以实现类似关系数据库单表查询的绝大部分功能，而且还支持对数据建立索引。

但是，MongoDB也不是万能的，同MySQL等关系型数据库相比，它们在针对不同的数据类型和事务要求上都存在自己独特的优势。在数据存储的选择中，坚持多样化原则，选择更好更经济的方式，而不是自上而下的统一化。

较常见的，我们可以直接用MongoDB来存储键值对类型的数据，如：验证码、Session等；由于MongoDB的横向扩展能力，也可以用来存储数据规模会在未来变的非常巨大的数据，
如：日志、评论等；由于MongoDB存储数据的弱类型，也可以用来存储一些多变json数据，如：与外系统交互时经常变化的JSON报文。而对于一些对数据有复杂的高事务性要求的操作，
如：账户交易等就不适合使用MongoDB来存储。
* */

/**
 * Created by zouzp on 2019/2/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImpl4MongodbTest {
    @Autowired
    private IUserService4Mongodb userService4Mongodb;

    @Before
    public void setUp() {
        userService4Mongodb.deleteAll();
    }

    @Test
    public void test() {
        // 创建三个User，并验证User总数
        userService4Mongodb.save(new User4Mongodb(1L, "aa", 30));
        userService4Mongodb.save(new User4Mongodb(2L, "bb", 40));
        userService4Mongodb.save(new User4Mongodb(3L, "cc", 50));
        Assert.assertEquals(3, userService4Mongodb.findAll().size());

        // 删除一个User，再验证User总数
        User4Mongodb u = userService4Mongodb.findByName("aa");
        userService4Mongodb.delete(u);
        Assert.assertEquals(2, userService4Mongodb.findAll().size());
    }
}
