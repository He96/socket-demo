package com.socket.conn;

import net.spy.memcached.MemcachedClient;

import java.net.InetSocketAddress;

/**
 * memcached连接服务
 */
public class MemcachedConn {

    private static final String url;
    private static final int port;

    public static final MemcachedConn mConn = new MemcachedConn();

    static {
        url = "127.0.0.1";
        port = 11211;
    }

    public MemcachedConn() {
    }
    public static MemcachedConn getInstance(){
        return mConn;
    }
    public MemcachedClient getmConn(){
        try {
            MemcachedClient memcachedClient = new MemcachedClient(new InetSocketAddress(url,port));
            System.out.println("memcached已连接");
            return memcachedClient;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }
}
