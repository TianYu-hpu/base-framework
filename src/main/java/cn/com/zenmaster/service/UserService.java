package cn.com.zenmaster.service;

import cn.com.zenmaster.entity.po.User;
import org.springframework.transaction.annotation.Transactional;

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
    int save(User user);

    /**
     * 更新，必须有主键
     * @param user
     * @return
     */
    @Override
    int update(User user);


    /**
     * 主键查询
     * @param id
     * @return
     */
    @Override
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

    @Transactional(rollbackFor = Exception.class)
    int updateByPrimaryKey(User user);

    void deleteByPrimaryKey(String id);
}
