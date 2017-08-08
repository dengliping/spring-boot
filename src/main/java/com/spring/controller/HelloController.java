package com.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dalp on 2017/8/4.
 */
@RestController //用RestController处理请求，返回的内容是json对象
public class HelloController {

    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String index(){
        return "hello deng";
    }
}
