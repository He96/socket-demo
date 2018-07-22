package com.socket.util;

import com.alibaba.fastjson.JSON;
import com.socket.conn.MemcachedConn;
import com.socket.model.User;
import net.spy.memcached.MemcachedClient;

import java.util.List;
import java.util.concurrent.Future;

public interface MemcachedService {

    boolean add(String key,List<User> values);

    List<User> get(String key);
}
