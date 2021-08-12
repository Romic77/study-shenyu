package com.example.studyshenyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.servlet.annotation.WebFilter;

@SpringBootApplication
@EnableDiscoveryClient
@WebFilter(filterName="HttpServletRequestReplacedFilter",urlPatterns="/*")
public class StudyShenyuApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyShenyuApplication.class, args);
    }

}
