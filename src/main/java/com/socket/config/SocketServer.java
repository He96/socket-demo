package com.socket.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.socket.config.SocketUtil;
import com.socket.model.User;
import com.socket.util.MemcachedService;
import com.socket.util.MemcachedServiceImpl;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 * socket服务端
 */
public class SocketServer extends Thread {

    private Socket mSocket = null;
    private static final MemcachedService ms = new MemcachedServiceImpl();

    public SocketServer(Socket socket) {
        this.mSocket = socket;
    }

    @Override
    public void run() {
        try {
            String resp = readSocket();
            //获取消息
            User user = JSON.parseObject(resp, User.class);
            //消息处理
            dealInfo(user);
        } catch (Exception e) {
        } finally {
            if (!mSocket.isClosed())
                try {
                    mSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private String readSocket() {
        if (null == mSocket)
            return "";
        String server_content = "";
        try {
            server_content = SocketUtil.Accept(mSocket);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return server_content;
    }

    private void writeResponse(String resp, Socket socket)
            throws Exception {
        SocketUtil.Send(resp, socket);
    }

    public void dealInfo(User user) throws Exception {
        List<User> list = ms.get("userList");
        for (User item : list) {
            if (item.getUserName().equals(user.getToUser())) {
                String userName = user.getUserName();
                user.setUserName(user.getToUser());
                user.setToUser(userName);
                SocketUtil.Send(JSON.toJSONString(user), item.getSocket());
            }
        }
    }

}
