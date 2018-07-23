
import com.alibaba.fastjson.JSON;
import com.socket.config.*;
import com.socket.model.User;
import java.net.Socket;
import java.util.Scanner;

public class Client001 {
    public static void main(String[] args){
        socketMain();
    }

    public static void socketMain(){
        try {
            Socket socket = SocketClient.openSocket();
            System.out.println("客户端001");
            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");
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
                SocketUtil.Send(JSON.toJSONString(user), socket);
                System.out.println("------------聊天记录------------------");
                System.out.println("你对"+user.getToUser()+"说:"+user.getMessage());
                Thread.sleep(500);
                String respstr = SocketUtil.Accept(socket);
                if(!"".equals(respstr) && respstr != null ){
                    User resUser = JSON.parseObject(respstr,User.class);
                    if(resUser.getStatus() == 2){
                        System.out.println("系统通知:" + resUser.getMessage());
                    }else{
                        System.out.println(user.getUserName()+"对你说:"+user.getMessage());
                    }
                }else{
                    System.out.println("暂无回应！");
                }

               /* User resUser = JSON.parseObject(respstr,User.class);
                System.out.println(resUser.getUserName()+":说" + resUser.getMessage());*/
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
