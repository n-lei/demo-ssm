package user.service;

import com.github.pagehelper.PageInfo;
import user.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 查询用户list信息 - 全部
     * @return
     */
    List<User> getAllList();

    /**
     * 查询用户list信息 - 分页数据
     * @param pageNumber 第几页
     * @param pageSize 每页数据量
     * @return
     */
    PageInfo<User> getPageList(Integer pageNumber, Integer pageSize);

    /**
     * 查询用户list信息 - 分页数据
     * @param pageNumber 第几页
     * @param pageSize 每页数据量
     * @return
     */
    PageInfo<User> getPageList(Integer pageNumber, Integer pageSize, Integer pageShowNumber);

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
