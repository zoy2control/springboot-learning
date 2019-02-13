package com.zoy.springboot.web.conf;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zouzp on 2019/1/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest // ·要加上 @SpringBootTest，@Autowired才会生效，why？。是否像 @SpringBootApplication一样，是个容器
public class PropertiesConfTest {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesConf.class);
    @Autowired
    private PropertiesConf propertiesConf;

    /**
     * ·@ConfigurationProperties与 @Value两种不同的获取 配置值的方式
     */
    @Test
    public void testAtValue() {
        String name = propertiesConf.getName();
        Assert.assertEquals("zoy", propertiesConf.getName());
    }



    /**
     * ·配置的参数之间的引用
     */
    @Test
    public void testPropertiesRef() {
        String birthdayDesc = propertiesConf.getBirthdayDesc();
        Assert.assertEquals("zoy的生日是1013", birthdayDesc);
    }



    /**
     * ·随机数
     */
    @Value("${com.zoy.springboot.random.string}")
    private String randomString;
    @Value("${com.zoy.springboot.random.int}")
    private int int1;
    @Value("${com.zoy.springboot.random.int2}")
    private int int2;
    @Value("${com.zoy.springboot.random.int3}")
    private int int3;
    @Value("${com.zoy.springboot.random.long}")
    private long longValue;

    @Test
    public void testRandom() {
        logger.error("随机字符串：" + randomString);
        logger.debug("随机int1：" + String.valueOf(int1));
        logger.info("随机int2：" + String.valueOf(int2));
        logger.error("随机int3：" + String.valueOf(int3));
        logger.debug("随机long：" + String.valueOf(longValue));
    }
}
