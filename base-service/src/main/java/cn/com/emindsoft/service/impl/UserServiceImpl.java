package cn.com.emindsoft.service.impl;

import cn.com.emindsoft.entity.po.User;
import cn.com.emindsoft.entity.po.UserExample;
import cn.com.emindsoft.enums.ResponseCodeEnum;
import cn.com.emindsoft.mapper.UserMapper;
import cn.com.emindsoft.service.UserService;
import cn.com.emindsoft.util.PasswordUtil;
import cn.com.emindsoft.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

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
    public Map<String, Object> save(User user) {
        //增加salt
        if(StringUtils.isEmpty(user.getSalt())) {
            user.setSalt(PasswordUtil.generatetPrivateSalt());
            user.setPassword(PasswordUtil.hashPassword(user.getSalt(), user.getPassword()));
        }
        user.preInsertOrUpdate();
        userMapper.insert(user);
        return ResponseUtil.success(ResponseCodeEnum.REGISTER_SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> update(User user) {
        user.preInsertOrUpdate();
        userMapper.updateByExample(user, buildExample(user));
        return ResponseUtil.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    @Override
    public User findByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findByExample(User param) {
        return userMapper.selectByExample(buildExample(param));
    }

    /**
     * 根据用户名进行登录
     * @param userName
     * @return
     */
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

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public Map<String, Object> login(User user) {
        if(Objects.isNull(user) || StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return ResponseUtil.fail(ResponseCodeEnum.LOGIN_FAILED);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), true);
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            return ResponseUtil.fail(ResponseCodeEnum.LOGIN_FAILED);
        } catch (UnknownAccountException uae) {
            return ResponseUtil.fail(ResponseCodeEnum.LOGIN_FAILED);
        } catch (ExcessiveAttemptsException eae) {
           return ResponseUtil.fail(ResponseCodeEnum.LOGIN_FAILED_MANY_TIMES);
        }
        User loginUser = findByUserName(user.getUsername());
        subject.getSession().setAttribute("user", loginUser);

        return ResponseUtil.success(ResponseCodeEnum.LOGIN_SUCCESS);
    }

    /**
     * 退出登录
     * @return
     */
    @Override
    public Map<String, Object> logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return ResponseUtil.success(ResponseCodeEnum.LOGOUT_SUCCESS);
    }

    /**
     * 更新密码
     * @param user
     * @return
     */
    @Override
    public Map<String, Object> updatePassword(User user) {
        user.setSalt(PasswordUtil.generatetPrivateSalt());
        user.setPassword(PasswordUtil.hashPassword(user.getSalt(), user.getPassword()));
        user.preInsertOrUpdate();
        update(user);
        return ResponseUtil.success(ResponseCodeEnum.UPDATE_PASSWORD_SUCCESS);
    }

    /**
     * 重置密码
     * @param user
     * @return
     */
    @Override
    public Map<String, Object> resetPassword(User user) {
        user.setSalt(PasswordUtil.generatetPrivateSalt());
        user.setPassword(PasswordUtil.hashPassword(user.getSalt(), user.getPassword()));
        user.preInsertOrUpdate();
        update(user);
        return ResponseUtil.success(ResponseCodeEnum.RESET_PASSWORD_SUCCESS);
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
