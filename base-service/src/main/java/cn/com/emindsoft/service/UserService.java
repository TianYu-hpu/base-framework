package cn.com.emindsoft.service;

import cn.com.emindsoft.entity.po.User;

import java.util.List;
import java.util.Map;

/**
 * @author tianyu
 * @Date: 2019/5/17 23:02
 * @Description:
 */
public interface UserService extends BaseService<User>{

    /**
     * 保存
     * @param user
     * @return
     */
    @Override
    Map<String, Object> save(User user);

    /**
     * 更新，必须有主键
     * @param user
     * @return
     */
    @Override
    Map<String, Object> update(User user);


    /**
     * 主键查询
     * @param id
     * @return
     */
    User findByPrimaryKey(String id);
    /**
     * 查询列表
     * @param param
     * @return
     */
    @Override
    List<User> findByExample(User param);

    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 登录
     * @param user
     * @return
     */
    Map<String, Object> login(User user);

    /**
     * 退出
     * @return
     */
    Map<String, Object> logout();

    Map<String, Object> updatePassword(User user);

    Map<String, Object> resetPassword(User user);
}
