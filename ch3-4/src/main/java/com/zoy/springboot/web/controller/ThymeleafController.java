package com.zoy.springboot.web.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zouzp on 2019/2/1.
 */
@RestController
public class ThymeleafController {
    @RequestMapping(value = "/thymeleaf/template/show", method = RequestMethod.GET)
    public ModelAndView getHost() {
        ModelAndView mv = new ModelAndView("thymeleaf_template_show");
        mv.addObject("host", "www.zoy.com");
        return mv;
    }
}
