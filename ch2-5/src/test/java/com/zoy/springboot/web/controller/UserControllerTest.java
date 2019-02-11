package com.zoy.springboot.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zouzp on 2019/2/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    /**
     * ·一个单元测试模拟所有情况>。
     * @throws Exception
     */
    @Test
    public void testUserController() throws Exception{
        RequestBuilder requestBuilder = null;// ·servlet

        /* ·查：第一次查 userList，肯定为空*/
        /* ·注意，路径/user最后要有个 /。？why*/
        requestBuilder = get("/user/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

        /* ·增：新增 id为1， name为“测试User”， age为18的 User*/
        requestBuilder = post("/user/").param("id","1").param("name","测试User").param("age", "18");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string("post user success"));

        /* ·查：因为上面 新增了一个User类，所以此处可以查到数据*/
        requestBuilder = get("/user/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"name\":\"测试User\",\"age\":18}]"));

        /* ·改*/
        requestBuilder = post("/user/1").param("name", "改变名字之后的测试User").param("age", "30");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string("success:put user by id"));

        /* ·查：查到数据是上面修改之后的*/
        requestBuilder = get("/user/1");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"name\":\"改变名字之后的测试User\",\"age\":30}"));

        /* ·删*/
        requestBuilder = delete("/user/1");
        mockMvc.perform(requestBuilder)
                .andExpect(content().string("success:delete user by id"));

        /* ·查：只有一条数据，删除之后，就只能查到 空数据了*/
        requestBuilder = get("/user/");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
}