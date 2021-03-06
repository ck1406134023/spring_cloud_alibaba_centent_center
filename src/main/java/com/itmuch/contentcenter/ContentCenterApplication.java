package com.itmuch.contentcenter;

import com.itmuch.contentcenter.configuration.GlobalFeignClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * @author chenkai
 */ //扫描mybatis哪些包里的接口
@MapperScan("com.itmuch")
@SpringBootApplication
@EnableFeignClients //(defaultConfiguration = GlobalFeignClientConfiguration.class)
public class ContentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }

    //在spring容器中，创建一个对象，类型RestTemplate 名称/id restTemplate
    //<bean id="restTemplate" class="xxx.RestTemplate">
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
