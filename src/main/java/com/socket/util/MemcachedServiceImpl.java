package com.socket.util;

import com.alibaba.fastjson.JSON;
import com.socket.conn.MemcachedConn;
import com.socket.model.User;
import net.spy.memcached.MemcachedClient;

import java.util.List;
import java.util.concurrent.Future;

public class MemcachedServiceImpl implements MemcachedService {

    private static MemcachedClient mc = null;

    static  {
        if(mc == null) {
            mc = MemcachedConn.getInstance().getmConn();
        }
    }

    public boolean add(String key,List<User> values){
        try {
            Future future = mc.set(key, 0, JSON.toJSONString(values));
            return (boolean) future.get();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<User> get(String key){
        List<User> list = JSON.parseArray(mc.get(key).toString(),User.class);
        return list;
    }
}
