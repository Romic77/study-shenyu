package com.example.studyshenyu.interceptor;


import com.example.studyshenyu.util.HttpServletRequestGetBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    private HttpServletRequestGetBody HttpServletRequestGetBody;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String body = HttpServletRequestGetBody.getBody(request);
        System.out.println(body);
        return true;
    }

}
