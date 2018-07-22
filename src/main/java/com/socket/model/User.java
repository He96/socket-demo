package com.socket.model;

import java.io.Serializable;
import java.net.Socket;

public class User implements Serializable {
    private String id;
    private String userName;
    private String passWord;
    private String message;
    private String toUser;
    private int status;
    private Socket socket;

    public User() {
    }
    public User(String id,String userName){
        this.id = id;
        this.userName = userName;
    }

    public User(String userName, Socket socket) {
        this.userName = userName;
        this.socket = socket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", message='" + message + '\'' +
                ", toUser='" + toUser + '\'' +
                ", status=" + status +
                ", socket=" + socket +
                '}';
    }
}
