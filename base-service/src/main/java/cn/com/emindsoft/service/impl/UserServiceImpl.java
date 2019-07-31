package cn.com.emindsoft.service.impl;

import cn.com.emindsoft.entity.po.User;
import cn.com.emindsoft.entity.po.UserExample;
import cn.com.emindsoft.enums.ExceptionEnum;
import cn.com.emindsoft.mapper.UserMapper;
import cn.com.emindsoft.service.UserService;
import cn.com.emindsoft.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author tianyu
 * @Date: 2019/5/17 23:07
 * @Description:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(User user) {
        user.preInsertOrUpdate();
        return userMapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(User user) {
        user.preInsertOrUpdate();
        return userMapper.updateByExample(user, buildExample(user));
    }

    @Override
    public User findByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findByExample(User param) {
        return userMapper.selectByExample(buildExample(param));
    }

    @Override
    public User findByUserName(String userName) {
        User queryParam = new User();
        queryParam.setUsername(userName);
        List<User> result = findByExample(queryParam);
        if(CollectionUtils.isEmpty(result)) {
            return null;
        } else {
            return result.get(0);
        }
    }

    @Override
    public Map<String, Object> login(User user) {
        if(Objects.isNull(user) || StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return ResponseUtil.fail(ExceptionEnum.LOGIN_FAILED);
        }
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        currentUser.login(token);
        Session session = currentUser.getSession();
        return ResponseUtil.success(ExceptionEnum.LOGIN_SUCCESS);
    }

    @Override
    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

    private UserExample buildExample(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();

        if(!Objects.isNull(user) && StringUtils.isNotEmpty(user.getId())) {
            criteria.andIdEqualTo(user.getId());
        }

        if(!Objects.isNull(user) && StringUtils.isNotEmpty(user.getUsername())) {
            criteria.andUsernameEqualTo(user.getUsername());
        }

        if(!Objects.isNull(user) && StringUtils.isNotEmpty(user.getEmail())) {
            criteria.andEmailEqualTo(user.getEmail());
        }

        if(!Objects.isNull(user) && StringUtils.isNotEmpty(user.getPhone())) {
            criteria.andPhoneEqualTo(user.getPhone());
        }

        example.setOrderByClause("create_time desc");

        return example;
    }
}
