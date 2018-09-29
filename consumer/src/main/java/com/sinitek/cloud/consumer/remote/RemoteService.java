package com.sinitek.cloud.consumer.remote;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cloud-producer", fallback = RemoteServiceFallBack.class)
public interface RemoteService {

    @RequestMapping("/hello")
    String hello(@RequestParam(value = "name") String name);

}
