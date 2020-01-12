package user.service.imp;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pub.spring.SpringPubTest;
import user.entity.User;

import java.util.List;

public class UserServiceImpTest extends SpringPubTest {

    @Autowired
    private UserServiceImp userService;

    @Test
    public void getUserList1() {
        List<User> list = userService.getUserList();
        System.out.println(list);
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getInfoById() {
        User user = userService.getInfoById(1);
        System.out.println(user);
    }

    @Test
    public void update() {
    }
}