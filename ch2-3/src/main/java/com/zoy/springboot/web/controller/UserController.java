package com.zoy.springboot.web.controller;

import com.zoy.springboot.web.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by zouzp on 2019/2/1.
 */
@RestController // ·原来@Controller返回json需要与 @ResponseBody配合，现在只用 @RestController即可返回 json对象。即 @RestController == @Controller + @ResponseBody
@RequestMapping("user")
public class UserController {
    /* ·User对象的增删改查都在这个 集合里面，不持久化。所以 userMap相当于 数据表*/
    static Map<Long, User> userMap = Collections.synchronizedMap(new HashMap<Long, User>()); // ·对于调用 static方法，倒是要先写好左边的 static，即 从左往右写。why?

    @RequestMapping(value="/", method = RequestMethod.GET)
    public List<User> getUserList() {
//        User user = userMap.get(id);// ·step1：根据入参 id获取用户，但这是 某个具体用户，而不是想要的 List<User>
//        Collection<User> values = userMap.values();// ·step2：Collections.values()方法 纵向获取 User形成一个 Collections。但返回类型是 List不是 Collections
//        List<User> values = (List)userMap.values();// ·step3：强制转换可以，似乎有点暴力
        List<User> result = new ArrayList<User>(userMap.values()); // ·step4：new List<>()方式优雅点
        return result;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {// ·绑定入参对象，不仅用 @ModelAttribute也可以用 @RequestParam。若非 对象，用 @PathVariable获取 url的参数
        if (null != user) {
            userMap.put(user.getId(), user);
            return "post user success";
        }
        return "post user fail";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id) {// ·@PathVariable，获取 url中的 路径参数。
        return userMap.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String putUserById(@PathVariable Long id, @ModelAttribute User userParam) {
        User user = userMap.get(id);
        if (null != user) {
            user.setName(userParam.getName());
            user.setAge(userParam.getAge());
            return "success:put user by id";
        }
        return "fail:put user by id";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUserById(@PathVariable Long id) {
        User remove = userMap.remove(id);// ·其实 RequestMothod.DELETE像是个 标识，真正 删除操作是由 Collections.remove()完成
        if (null != remove) {
            return "success:delete user by id";
        }
        return "fail:delete user by id";
    }
}
