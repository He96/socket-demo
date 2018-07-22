import com.socket.config.Listener;

public class ServerMain {

    public static void main(String[] args){
        Listener.getInstance().start();
    }
}
