package user.service;

import user.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 查询用户list信息
     * @return
     */
    List<User> getUserList();

    /**
     * 保存用户
     * @param user
     */
    void save(User user);

    /**
     * 删除用户
     * @param id 用户id
     */
    void delete(long id);

    /**
     * 更新前获取用户信息
     * @param id
     * @return
     */
    User getInfoById(long id);

    /**
     * 更新用户
     * @param user
     */
    boolean update(User user);

}
