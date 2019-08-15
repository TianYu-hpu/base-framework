package cn.com.emindsoft.service.impl;

import cn.com.emindsoft.entity.po.UserRole;
import cn.com.emindsoft.entity.po.UserRoleExample;
import cn.com.emindsoft.enums.DelFlagEnum;
import cn.com.emindsoft.mapper.UserRoleMapper;
import cn.com.emindsoft.service.UserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tianyu
 * @Date: 2019/5/17 23:07
 * @Description:
 */
@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(UserRole record) {
        record.preInsertOrUpdate();
        return userRoleMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(UserRole record) {
        record.preInsertOrUpdate();
        return userRoleMapper.updateByExample(record, buildExample(record));
    }

    @Override
    public UserRole findByPrimaryKey(String id) {
        UserRole result = userRoleMapper.selectByPrimaryKey(id);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPrimaryKey(String id) {
        userRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<UserRole> findByExample(UserRole param) {
        return userRoleMapper.selectByExample(buildExample(param));
    }

    @Override
    public PageInfo<UserRole> findPageByExample(UserRole param) {
        PageInfo<UserRole> pageInfo = PageHelper.startPage(0, 10).doSelectPageInfo(() ->
                userRoleMapper.selectByExample(buildExample(param)));
        return pageInfo;
    }

    private UserRoleExample buildExample(UserRole record) {
        UserRoleExample example = new UserRoleExample();
        UserRoleExample.Criteria criteria = example.createCriteria();

        criteria.andDelFlagEqualTo(DelFlagEnum.N.getCode());
        example.setOrderByClause("create_time desc");
        return example;
    }
}
