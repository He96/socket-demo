
import com.alibaba.fastjson.JSON;
import com.socket.config.*;
import com.socket.conn.MemcachedConn;
import com.socket.model.User;
import com.socket.util.MemcachedService;
import com.socket.util.MemcachedServiceImpl;
import net.spy.memcached.MemcachedClient;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args){
        socketMain();
        //getMemc();
    }

    public static void getMemc(){
        MemcachedClient mc = MemcachedConn.getInstance().getmConn();
        System.out.println("he:" + mc.get("he"));
    }

    public static void socketMain(){
        try {

            System.out.println("客户端001");
            Scanner scanner = new Scanner(System.in);
            System.out.println("------------填写用户信息-------------");
            System.out.println("姓名:");
            User user = new User();
            user.setUserName(scanner.next());
            user.setStatus(1);
            System.out.println("收信人:");
            user.setToUser(scanner.next());
            while (true) {
                System.out.println("消息:");
                String info = scanner.next();
                user.setMessage(info);
                Socket socket = SocketClient.openSocket();
                SocketUtil.Send(JSON.toJSONString(user), socket);
                Thread.sleep(500);
                System.out.println("------------聊天记录------------------");
                String respstr = SocketUtil.Accept(socket);
                System.out.println("res001"+respstr);
               /* User resUser = JSON.parseObject(respstr,User.class);
                System.out.println(resUser.getUserName()+":说" + resUser.getMessage());*/
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
