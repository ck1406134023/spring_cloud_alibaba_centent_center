package com.itmuch.contentcenter.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;


/**
 * @author chenkai
 * 不要加@configuration,否则必须挪到@configuration能被扫描到的以外
 */
public class GlobalFeignClientConfiguration {
    @Bean
    public Logger.Level level(){
        // 让feign打印所有的请求细节
        return Logger.Level.FULL;
    }
}
