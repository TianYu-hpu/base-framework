package cn.com.emindsoft.service.impl;

import cn.com.emindsoft.entity.po.User;
import cn.com.emindsoft.entity.po.UserExample;
import cn.com.emindsoft.mapper.UserMapper;
import cn.com.emindsoft.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public List<User> findByExample(User param) {
        return userMapper.selectByExample(buildExample(param));
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
