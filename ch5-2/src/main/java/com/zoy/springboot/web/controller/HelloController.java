package com.zoy.springboot.web.controller;

import com.zoy.springboot.web.exception.JsonException;
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

    @GetMapping("exception/hello")
    public String helloException() throws Exception{
        throw new Exception("你发生错误啦啦啦");
    }

    @GetMapping("exception/json")
    public String jsonException() throws JsonException{
        throw new JsonException("你发送错误啦啦啦，Json格式的");
    }
}
