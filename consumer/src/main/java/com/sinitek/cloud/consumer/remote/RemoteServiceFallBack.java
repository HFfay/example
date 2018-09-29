package com.sinitek.cloud.consumer.remote;

import org.springframework.stereotype.Component;

@Component
public class RemoteServiceFallBack implements RemoteService{

    @Override
    public String hello(String name) {
        return "can't get remote msg, param is[" + name + "]";
    }
}
