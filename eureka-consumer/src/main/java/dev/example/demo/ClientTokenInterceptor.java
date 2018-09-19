package dev.example.demo;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class ClientTokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("accesstoken", "asdasdsad");
    }
}
