package com.zoy.springboot.web.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by zouzp on 2019/1/31.
 */
@Component
@Data // ·lombok插件
//@ConfigurationProperties(prefix = "com.zoy.springboot")
// ·方式一：@ConfigurationProperties()批量读取配置并绑定到 实体类。缺点，后缀名称与 实体类属性名称要一一对应，改动任意一个都会导致绑定不到
public class PropertiesConf {
    @Value("${com.zoy.springboot.name}") // ·方式二：@Value()单个绑定
    private String name;
    @Value("${com.zoy.springboot.birthday}")
    private String birthday;
    @Value("${com.zoy.springboot.birthday.desc}")
    private String birthdayDesc;
}
