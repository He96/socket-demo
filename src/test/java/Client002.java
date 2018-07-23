
import com.alibaba.fastjson.JSON;

import com.socket.config.SocketClient;
import com.socket.config.SocketUtil;
import com.socket.model.User;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client002 {
    public static void main(String[] args) {
        socketMain();
    }

    public static void socketMain() {
        try {
            Socket socket = SocketClient.openSocket();
            List<User> userList = new ArrayList<>();
            System.out.println("客户端002");
            Scanner scanner = new Scanner(System.in);
           /* System.out.println("------------填写用户信息-------------");
            System.out.println("姓名:");
            User user = new User();
            user.setUserName(scanner.next());
            user.setStatus(1);
            System.out.println("收信人:");
            user.setToUser(scanner.next());*/
            //while (true) {
                /*System.out.println("消息:");
                String info = scanner.next();
                user.setMessage(info);
                userList.add(user);
                SocketUtil.Send(JSON.toJSONString(user), socket);*/
            Thread.sleep(500);
            System.out.println("------------服务器回复列表------------------");
            while (true) {
                String respstr = SocketUtil.Accept(socket);
                if (!"".equals(respstr)) {
                    User user = JSON.parseObject(respstr, User.class);
                    if (user.getStatus() == 2) {
                        System.out.println("系统消息：" + user.getMessage());
                    } else {
                        System.out.println(user.getUserName() + "对你说:" + user.getMessage());
                    }

                }
            }
                /*User resUser = JSON.parseObject(respstr,User.class);
                System.out.println(resUser.getUserName()+":说" + resUser.getMessage());*/
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
