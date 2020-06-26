package com.itmuch.contentcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;


//扫描mybatis哪些包里的接口
@MapperScan("com.itmuch")
@SpringBootApplication
class ContentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }

    //在spring容器中，创建一个对象，类型RestTemplate 名称/id restTemplate
    //<bean id="restTemplate" class="xxx.RestTemplate">
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
