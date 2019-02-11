package com.zoy.springboot.web.controller;

import com.zoy.springboot.web.model.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by zouzp on 2019/2/11.
 */
/*
*
@Api：用在请求的类上，表示对类的说明
    tags="说明该类的作用，可以在UI界面上看到的注解"
    value="该参数没什么意义，在UI界面上也看到，所以不需要配置"

@ApiOperation：用在请求的方法上，说明方法的用途、作用
    value="说明方法的用途、作用"
    notes="方法的备注说明"

@ApiImplicitParams：用在请求的方法上，表示一组参数说明
    @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
        name：参数名
        value：参数的汉字说明、解释
        required：参数是否必须传
        paramType：参数放在哪个地方
            · header --> 请求参数的获取：@RequestHeader
            · query --> 请求参数的获取：@RequestParam
            · path（用于restful接口）--> 请求参数的获取：@PathVariable
            · body（不常用）
            · form（不常用）
        dataType：参数类型，默认String，其它值dataType="Integer"
        defaultValue：参数的默认值

@ApiResponses：用在请求的方法上，表示一组响应
    @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
        code：数字，例如400
        message：信息，例如"请求参数没填好"
        response：抛出异常的类

@ApiModel：用于响应类上，表示一个返回响应数据的信息
            （这种一般用在post创建的时候，使用@RequestBody这样的场景，
            请求参数无法使用@ApiImplicitParam注解进行描述的时候）

@ApiModelProperty：用在属性上，描述响应类的属性
* */
// ·原来@Controller返回json需要与 @ResponseBody配合，现在只用 @RestController即可返回 json对象。即 @RestController == @Controller + @ResponseBody
// ·http://127.0.0.1:8080/swagger-ui.html#/ 查看生成的 swagger2文档
@RestController
@RequestMapping("/swagger2/user")
public class SwaggerController {
    /* ·User对象的增删改查都在这个 集合里面，不持久化。所以 userMap相当于 数据表*/
    static Map<Long, User> userMap = Collections.synchronizedMap(new HashMap<Long, User>()); // ·对于调用 static方法，倒是要先写好左边的 static，即 从左往右写。why?

    @ApiOperation(value = "获取用户列表", notes = "")
    @RequestMapping(value="/", method = RequestMethod.GET)
    public List<User> getUserList() {
//        User user = userMap.get(id);// ·step1：根据入参 id获取用户，但这是 某个具体用户，而不是想要的 List<User>
//        Collection<User> values = userMap.values();// ·step2：Collections.values()方法 纵向获取 User形成一个 Collections。但返回类型是 List不是 Collections
//        List<User> values = (List)userMap.values();// ·step3：强制转换可以，似乎有点暴力
        List<User> result = new ArrayList<User>(userMap.values()); // ·step4：new List<>()方式优雅点
        return result;
    }

    @ApiOperation(value = "创建用户", notes = "")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="/", method = RequestMethod.POST)
    public String postUser(@RequestBody User user) {// ·绑定入参对象，不仅用 @ModelAttribute也可以用 @RequestParam。若非 对象，用 @PathVariable获取 url的参数
                                                        // ·注意，此处用到 @RequestBody，而不是 @ModelAttribute，具体看 页面展示效果（一个参数一条条展示，一个是 Json对象整体展示）
        if (null != user) {
            userMap.put(user.getId(), user);
            return "post user success";
        }
        return "post user fail";
    }

    @ApiOperation(value = "根据 id获取用户", notes = "根据url中的 id获取用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Long id) {// ·@PathVariable，获取 url中的 路径参数。
        return userMap.get(id);
    }

    @ApiOperation(value = "修改用户详细信息", notes = "根据url中的 id修改用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "userParam", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String putUserById(@PathVariable Long id, @RequestBody User userParam) {// ·注意，此处用到 @RequestBody，而不是 @ModelAttribute，具体看 页面展示效果（一个参数一条条展示，一个是 Json对象整体展示）
        User user = userMap.get(id);
        if (null != user) {
            user.setName(userParam.getName());
            user.setAge(userParam.getAge());
            return "success:update user by id";
        }
        return "fail:update user by id";
    }

    @ApiOperation(value = "删除用户", notes = "根据url中的 id删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUserById(@PathVariable Long id) {
        User remove = userMap.remove(id);// ·其实 RequestMothod.DELETE像是个 标识，真正 删除操作是由 Collections.remove()完成
        if (null != remove) {
            return "success:delete user by id";
        }
        return "fail:delete user by id";
    }
}
