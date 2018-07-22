package com.socket.config;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static Socket socket = null;

    public static Socket openSocket() {
        try {
            socket = new Socket("localhost", 10222);
            return socket;
        } catch (Exception e) {
            System.out.println("客户端未连接...");
        }
        return null;
    }

    public static void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("客户端关闭异常...");
        }
    }
}
