package com.example.studyshenyu.controller;


import com.example.studyshenyu.util.OkHttpTools;
import org.apache.shenyu.client.springcloud.annotation.ShenyuSpringCloudClient;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/test")
@ShenyuSpringCloudClient(path = "/test/**")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return null;
    }

    @PostMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("username:" + username + ";password:" + password);
        return "save success";
    }
}
