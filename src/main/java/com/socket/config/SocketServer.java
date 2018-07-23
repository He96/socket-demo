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
            dealInfo(resp);
        } catch (Exception e) {
            e.printStackTrace();
        } /*finally {
            if (!mSocket.isClosed())
                try {
                    mSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }*/
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

    public void dealInfo(String userInfo) throws Exception {
        if (!"".equals(userInfo)) {
            User user = JSON.parseObject(userInfo, User.class);
            user.setStatus(1);
            List<User> list = ChatManager.getChatManager().get();//ms.get("userList");
            //服务器转发消息
            for (User item : list) {
                if (item.getUserName().equals(user.getToUser())) {
                    writeResponse(JSON.toJSONString(user), item.getSocket());
                }
            }
            //回复客户端消息
            user.setStatus(2);
            user.setMessage("发送成功!");
            SocketUtil.Send(JSON.toJSONString(user), mSocket);
        } else {
            System.out.println("未收到消息");
        }
    }

}
