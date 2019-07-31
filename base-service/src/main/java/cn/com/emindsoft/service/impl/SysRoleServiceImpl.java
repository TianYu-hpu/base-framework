package cn.com.emindsoft.service.impl;

import cn.com.emindsoft.entity.po.SysRole;
import cn.com.emindsoft.entity.po.SysRoleExample;
import cn.com.emindsoft.mapper.SysRoleMapper;
import cn.com.emindsoft.service.SysRoleService;
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
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(SysRole user) {
        user.preInsertOrUpdate();
        return sysRoleMapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(SysRole user) {
        user.preInsertOrUpdate();
        return sysRoleMapper.updateByExample(user, buildExample(user));
    }

    @Override
    public SysRole findByPrimaryKey(String id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRole> findByExample(SysRole param) {
        return sysRoleMapper.selectByExample(buildExample(param));
    }

    private SysRoleExample buildExample(SysRole user) {
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();



        example.setOrderByClause("create_time desc");

        return example;
    }
}
