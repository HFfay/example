package dev.example.demo.tcp;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

@Component
public class Server implements InitializingBean {

    public void start() throws Exception{

        ServerSocket server = new ServerSocket(9880);
        Socket client = null;
        boolean f = true;
        while(f){
            System.out.println("等待客户端连接。。。。");
            client = server.accept();
            System.out.println("与客户端连接成功！");

            deal(client.getInputStream());

            send(client.getOutputStream());
        }
        server.close();
    }

    public void deal(InputStream in) throws Exception {
        BufferedReader buf = new BufferedReader(new InputStreamReader(in));
        String str =  buf.readLine();
        System.out.println("客户端发来的数据：" + str);
    }

    public void send(OutputStream out) throws Exception {
        PrintStream ps = new PrintStream(out);
        ps.println("server：time = " + new Date());
        ps.close();
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Server().start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
