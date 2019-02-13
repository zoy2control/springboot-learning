package com.zoy.springboot.service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by zouzp on 2019/2/13.
 */
@Data
public class User4Mongodb {
    private Long id;
    private String userName;
    private Integer age;

    public User4Mongodb(Long id, String userName, Integer age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }
}
