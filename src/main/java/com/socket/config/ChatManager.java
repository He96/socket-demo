package com.socket.config;

import com.socket.model.User;

import java.util.ArrayList;
import java.util.List;

public class ChatManager {
    private ChatManager(){

    }
    private static final ChatManager cm = new ChatManager();

    public static ChatManager getChatManager(){
        return cm;
    }

    List<User> list = new ArrayList<>();

    public void add(User user){
        list.add(user);
    }

    public List<User> get(){
        return list;
    }
}
