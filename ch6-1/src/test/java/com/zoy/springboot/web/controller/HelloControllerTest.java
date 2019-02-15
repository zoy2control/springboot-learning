package com.zoy.springboot.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zoypong on 2019/1/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
/**
 * ·模拟http请求
 */
public class HelloControllerTest {
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        try {
            // ·创建 HelloController对象可以在 @Before函数中创建，并传递到 MockMvcBuilders.standaloneSetup()函数中
            mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/index").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("hello springboot2.0"));
    }

}