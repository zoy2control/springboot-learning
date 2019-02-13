package com.zoy.springboot.service.model;

import lombok.Data;

/**
 * Created by zouzp on 2019/2/13.
 */
@Data
public class User4Mybatis {
    private Long id;
    private String name;
    private Integer age;

    public User4Mybatis(){}

    public User4Mybatis(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
