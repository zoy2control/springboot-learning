package com.zoy.springboot.web.controller;


import com.zoy.springboot.web.model.User4Xml;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zouzp on 2019/2/11.
 */
@Controller
public class User4XmlController {

    @PostMapping(value = "/xml/user/create")
    @ResponseBody
    public User4Xml create(@RequestBody User4Xml user) {
        user.setName("zoooooooooooy:" + user.getName());
        user.setAge(user.getAge() + 10);
        return user;
    }
}
