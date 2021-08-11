package com.example.studyshenyu.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class OkHttpToolsTest {
    @Test
    public void testGet() throws IOException {
        String result = OkHttpTools.getInstance().get("http://localhost:8060/test/hello");
        Assert.assertEquals("hello shenyu", result);
    }

    @Test
    public void testPost() throws IOException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", "zhangsan");
        paramMap.put("password", 123456);
        String result = OkHttpTools.getInstance().post("http://localhost:8060/test/save", paramMap);
        Assert.assertEquals("save success", result);
    }
}
