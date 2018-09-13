package dev.example.demo;

import org.springframework.stereotype.Component;

@Component
public class RemoteServiceFallBack implements RemoteService{

    @Override
    public String hello(String name) {
        return "can't talk to " + name;
    }
}
