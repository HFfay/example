package dev.example.demo.tcp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

@RestController
public class Client {


    @RequestMapping("/startClient")
    public String startClient() throws Exception {
        Socket client = null;
        try {
            client = new Socket("127.0.0.1", 9880);
            client.setSoTimeout(10000);

            //发送数据到服务端
            PrintStream out = new PrintStream(client.getOutputStream());
            out.println("client 11111111");

            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String echo = buf.readLine();
            System.out.println("服务端的答复：" + echo);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(client != null){
                client.close();
            }
        }

        return "";
    }

}
