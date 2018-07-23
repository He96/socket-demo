
import com.socket.model.User;
import com.socket.util.MemcachedService;
import com.socket.util.MemcachedServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        MemcachedService memcachedService = new MemcachedServiceImpl();
        List<User> list1 = new ArrayList<>();
        list1.add(new User("001", "he"));
        list1.add(new User("002", "su"));
        memcachedService.add("TestList", list1);
        List<User> list = memcachedService.get("TestList");
        if (list != null && list.size() > 0) {
            for (User item : list) {
                System.out.println(item.toString());
            }
        }
    }
}
