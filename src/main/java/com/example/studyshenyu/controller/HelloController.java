package com.example.studyshenyu.controller;


import org.apache.shenyu.client.springcloud.annotation.ShenyuSpringCloudClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@ShenyuSpringCloudClient(path = "/test/**")
public class HelloController {

    @GetMapping("/hello")
    public String findByUserId() {
       return "hello shenyu";
    }
}
