package com.zoy.springboot.service.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zouzp on 2019/2/13.
 */
@Data
public class User4Redis implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;
    private Integer age;

    public User4Redis(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }
}
