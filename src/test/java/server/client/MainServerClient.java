package server.client;

import com.socket.config.SocketUtil;

import java.net.ServerSocket;
import java.net.Socket;

public class MainServerClient {
    public static void main(String[] args) {
        server();
        client();
    }

    //服务端
    public static void server() {
        try {
            Socket socket = new Socket("localhost", 10020);
            SocketUtil.Send("你好，我是客户端", socket);
            String info = SocketUtil.Accept(socket);
            Thread.sleep(500);
            System.out.println("服务器说:" + info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //客户端
    public static void client() {
        try {
            ServerSocket serverSocket = new ServerSocket(10020);
            System.out.println("等待客户端连接");
            Socket socket = serverSocket.accept();
            System.out.println("连接成功！");
            String info = SocketUtil.Accept(socket);
            System.out.println("客户端说：" + info);
            SocketUtil.Send("我已收到您的消息", socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
