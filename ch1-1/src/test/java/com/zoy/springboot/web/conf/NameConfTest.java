package com.zoy.springboot.web.conf;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by zouzp on 2019/1/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest // ·要加上 @SpringBootTest，@Autowired才会生效，why？。是否像 @SpringBootApplication一样，是个容器
public class NameConfTest {
    @Autowired
    private NameConf nameConf;

    /**
     * ·@ConfigurationProperties与 @Value两种不同的获取 配置值的方式
     */
    @Test
    public void testAtValue() {
        String name = nameConf.getName();
        Assert.assertEquals("zoy", nameConf.getName());
    }

    /**
     * ·配置的参数之间的引用
     */
    @Test
    public void testPropertiesRef() {
        String birthdayDesc = nameConf.getBirthdayDesc();
        Assert.assertEquals("zoy的生日是1013", birthdayDesc);
    }
}
