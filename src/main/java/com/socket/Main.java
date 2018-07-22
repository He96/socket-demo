package com.socket;
import com.alibaba.fastjson.JSON;
import com.socket.config.Listener;
import com.socket.config.SocketClient;
import com.socket.config.SocketUtil;
import com.socket.conn.*;
import com.socket.model.User;
import com.socket.util.MemcachedService;
import com.socket.util.MemcachedServiceImpl;
import net.spy.memcached.MemcachedClient;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args){
        /*List<User> list = new ArrayList<>();
        User a = new User("001","he");
        list.add(a);
        a = new User("002","mathew");
        list.add(a);
        System.out.println("size:"+list.size());
        MemcachedService ms = new MemcachedServiceImpl();
        //ms.add("userList",list);
        List<User> userList = ms.get("userList");
        for(User item : userList){
            System.out.println(item.toString());
        }*/
        socketMain();
    }

    public static void socketMain(){
        try {
            List<User> userList = new ArrayList<>();
            MemcachedService ms = new MemcachedServiceImpl();
            System.out.println("客户端002");
            Scanner scanner = new Scanner(System.in);
            System.out.println("------------填写用户信息-------------");
            System.out.println("姓名:");
            User user = new User();
            user.setUserName(scanner.next());
            user.setStatus(1);
            System.out.println("收信人:");
            user.setToUser(scanner.next());
            while (true) {
                Socket socket = SocketClient.openSocket();
                User userTo = new User(user.getUserName(), socket);
                userList.add(userTo);
                ms.add("userList",userList);
                System.out.println("消息:");
                String info = scanner.next();
                user.setMessage(info);
                userList.add(user);
                ms.add("userList",userList);
                SocketUtil.Send(JSON.toJSONString(user), socket);
                Thread.sleep(500);
                System.out.println("------------服务器回复列表------------------");
                String respstr = SocketUtil.Accept(socket);
                System.out.println("res002"+respstr);
                /*User resUser = JSON.parseObject(respstr,User.class);
                System.out.println(resUser.getUserName()+":说" + resUser.getMessage());*/
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
