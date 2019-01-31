package com.zoy.springboot.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zoypong on 2019/1/30.
 */
@RestController
public class HelloController {
    @GetMapping("index")
    public String helloSpringBoot() {
        return "hello springboot2.0";
    }
}
