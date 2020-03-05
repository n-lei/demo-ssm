package user.service.imp;

import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pub.spring.SpringPubTest;
import user.entity.User;

import java.util.List;

public class UserServiceImpTest extends SpringPubTest {

    @Autowired
    private UserServiceImp userService;

    @Test
    public void getAllList() {
        List<User> list = userService.getAllList();
        System.out.println(list);
    }

    @Test
    public void getPageList(){
        PageInfo<User> user = userService.getPageList(1 ,10, 6);
        System.out.println(user);
        /*System.out.println(user.getList());
        System.out.println(user.getPages());
        System.out.println(user.getPageNum());*/
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