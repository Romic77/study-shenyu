/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.studyshenyu.util;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * OkHttpTools.
 */
public final class OkHttpTools {

    /**
     * The constant JSON.
     */
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static final OkHttpTools OK_HTTP_TOOLS = new OkHttpTools();


    private final OkHttpClient client;

    private OkHttpTools() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        client = builder.build();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static OkHttpTools getInstance() {
        return OK_HTTP_TOOLS;
    }

    /**
     * post string.
     *
     * @param url  the url
     * @param json the json
     * @return the string
     * @throws IOException the io exception
     */
    public String post(final String url, final String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return client.newCall(request).execute().body().string();
    }

    /**
     * get string.
     *
     * @param url the url
     * @return the string
     * @throws IOException the io exception
     */
    public String get(final String url) throws IOException {
        Request request = new Request.Builder().get()
                .url(url)
                .build();
        return client.newCall(request).execute().body().string();
    }

    /**
     * get with paramMap
     *
     * @param url      the url
     * @param paramMap the paramMap
     * @return the string
     */
    public String get(String url, Map<String, Object> paramMap) throws IOException {
        // 拼接请求参数
        if (!paramMap.isEmpty()) {
            StringBuffer buffer = new StringBuffer(url);
            buffer.append('?');
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                buffer.append(entry.getKey());
                buffer.append('=');
                buffer.append(entry.getValue());
                buffer.append('&');
            }
            buffer.deleteCharAt(buffer.length() - 1);
            url = buffer.toString();
        }
        Request request = new Request.Builder().get()
                .url(url)
                .build();
        return client.newCall(request).execute().body().string();
    }

    /**
     * post
     *
     * @param url the url
     * @param paramMap paramMap
     * @return the string
     * @throws IOException IOException
     */
    public String post(String url, Map<String, Object> paramMap) throws IOException {
        FormBody.Builder formBody = new FormBody.Builder();
        if (!paramMap.isEmpty()) {
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                formBody.add(entry.getKey(), entry.getValue().toString());
            }
        }
        RequestBody body = formBody.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return client.newCall(request).execute().body().string();
    }
}
