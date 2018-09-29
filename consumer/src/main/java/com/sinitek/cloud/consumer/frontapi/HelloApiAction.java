package com.sinitek.cloud.consumer.frontapi;

import com.sinitek.cloud.consumer.remote.RemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApiAction {

    @Autowired
    RemoteService remoteService;

    @Value("${server.port}")
    String port;

    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {
        return "hear is [" + port + "], I got [ " + remoteService.hello(name) + " ] from remote !!!";
    }

}
