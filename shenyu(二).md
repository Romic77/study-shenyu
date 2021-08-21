# shenyu(二)

## 入口

从`@ShenyuSpringCloudClient`注解入手,`SpringCloudClientBeanPostProcessor`

```java
if (controller != null || restController != null || requestMapping != null) {
            String prePath = "";
            ShenyuSpringCloudClient clazzAnnotation = AnnotationUtils.findAnnotation(bean.getClass(), ShenyuSpringCloudClient.class);
            if (Objects.isNull(clazzAnnotation)) {
                return bean;
            }
            if (clazzAnnotation.path().indexOf("*") > 1) {
                String finalPrePath = prePath;
                //Disruptor发布事件
                executorService.execute(() -> publisher.publishEvent(buildMetaDataDTO(clazzAnnotation, finalPrePath)));
                return bean;
            }
            prePath = clazzAnnotation.path();
            final Method[] methods = ReflectionUtils.getUniqueDeclaredMethods(bean.getClass());
            for (Method method : methods) {
                ShenyuSpringCloudClient shenyuSpringCloudClient = AnnotationUtils.findAnnotation(method, ShenyuSpringCloudClient.class);
                if (Objects.nonNull(shenyuSpringCloudClient)) {
                    String finalPrePath = prePath;
                    executorService.execute(() -> publisher.publishEvent(buildMetaDataDTO(shenyuSpringCloudClient, finalPrePath)));
                }
            }
        }
```

事件已经发布了,现在就需要看是如何消费的.

目前我查看了半天源码,发现消费的代码逻辑封装在`ShenyuClientMetadataExecutorSubscriber#executor()`

1. 首先通过netty建立连接

2. `shenyuClientRegisterRepository.persistInterface(metaDataRegisterDTO);`持久化接口,消费前面发布的事件

3. ```
   shenyu:
     client:
       registerType: http #zookeeper #etcd #nacos #consul
       serverLists: http://localhost:9095 #localhost:2181 #http://localhost:2379 #localhost:8848
       props:
         contextPath: /shenyu-study
         port: 8060
         nacosNameSpace: ShenyuRegisterCenter
   ```

   因为我们使用的注册类型是http;

   SPI机制:

   META-INF.shenyu

   org.apache.shenyu.register.client.api.ShenyuClientRegisterRepository

   ```
   http=org.apache.shenyu.register.client.http.HttpClientRegisterRepository
   ```

4. 后面需要搞懂,如何SPI获取,加载shenyu.client.registerType的注册类型