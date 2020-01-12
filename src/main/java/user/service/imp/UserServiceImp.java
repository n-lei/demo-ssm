package user.service.imp;

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
    public List<User> getUserList(){
        return userMapper.getUserList();
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