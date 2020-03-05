package user.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import user.dao.UserMapper;
import user.entity.User;
import user.service.UserService;

import java.util.List;

@Scope("prototype")
@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllList(){
        return userMapper.getAllList();
    }

    @Override
    public PageInfo<User> getPageList(Integer pageNumber, Integer pageSize) {
        // 显示分页数 10表示设置连续显示的页号数目 1 2 3 4 5 6 7 8 9 10
        return getPageList(pageNumber, pageSize, 10);
    }

    @Override
    public PageInfo<User> getPageList(Integer pageNumber, Integer pageSize, Integer pageShowNumber) {
        PageHelper.startPage(pageNumber, pageSize);
        List<User> users = userMapper.getAllList();
        // 显示分页数 pageShowNumber 表示设置连续显示的页号数目 1 2 3 4 5 ...
        return new PageInfo<>(users, pageShowNumber);
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public void delete(long id) {
        userMapper.delete(id);
    }

    @Override
    public User getInfoById(long id){
        return userMapper.getInfoById(id);
    }

    @Override
    public boolean update(User user){
        return userMapper.update(user);
    }
}