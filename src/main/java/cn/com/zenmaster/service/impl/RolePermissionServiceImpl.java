package cn.com.zenmaster.service.impl;

import cn.com.zenmaster.entity.po.RolePermission;
import cn.com.zenmaster.entity.po.RolePermissionExample;
import cn.com.zenmaster.enums.DelFlagEnum;
import cn.com.zenmaster.mapper.RolePermissionMapper;
import cn.com.zenmaster.service.RolePermissionService;
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
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(RolePermission record) {
        record.preInsertOrUpdate();
        return rolePermissionMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(RolePermission record) {
        record.preInsertOrUpdate();
        return rolePermissionMapper.updateByExample(record, buildExample(record));
    }

    @Override
    public RolePermission findByPrimaryKey(String id) {
        RolePermission result = rolePermissionMapper.selectByPrimaryKey(id);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPrimaryKey(String id) {
        rolePermissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<RolePermission> findByExample(RolePermission param) {
        return rolePermissionMapper.selectByExample(buildExample(param));
    }

    @Override
    public PageInfo<RolePermission> findPageByExample(RolePermission param) {
        PageInfo<RolePermission> pageInfo = PageHelper.startPage(0, 10).doSelectPageInfo(() ->
                rolePermissionMapper.selectByExample(buildExample(param)));
        return pageInfo;
    }

    private RolePermissionExample buildExample(RolePermission record) {
        RolePermissionExample example = new RolePermissionExample();
        RolePermissionExample.Criteria criteria = example.createCriteria();

        criteria.andDelFlagEqualTo(DelFlagEnum.N.getCode());
        example.setOrderByClause("create_time desc");
        return example;
    }
}
