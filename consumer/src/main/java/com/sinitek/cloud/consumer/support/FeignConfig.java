package com.sinitek.cloud.consumer.support;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    ClientTokenInterceptor getClientTokenInterceptor(){
        return new ClientTokenInterceptor();
    }

    @Bean
    FeignHystrixConcurrencyStrategy getFeignHystrixConcurrencyStrategy(){
        return new FeignHystrixConcurrencyStrategy();
    }

}
