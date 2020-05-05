package cn.com.zenmaster.service.impl;

import cn.com.zenmaster.entity.po.SysPermission;
import cn.com.zenmaster.entity.po.SysPermissionExample;
import cn.com.zenmaster.enums.DelFlagEnum;
import cn.com.zenmaster.mapper.SysPermissionMapper;
import cn.com.zenmaster.service.SysPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(SysPermission record) {
        record.preInsertOrUpdate();
        return sysPermissionMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(SysPermission record) {
        record.preInsertOrUpdate();
        return sysPermissionMapper.updateByExample(record, buildExample(record));
    }

    @Override
    public SysPermission findByPrimaryKey(String id) {
        SysPermission result = sysPermissionMapper.selectByPrimaryKey(id);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPrimaryKey(String id) {
        sysPermissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysPermission> findByExample(SysPermission param) {
        return sysPermissionMapper.selectByExample(buildExample(param));
    }

    @Override
    public PageInfo<SysPermission> findPageByExample(SysPermission param) {
        PageInfo<SysPermission> pageInfo = PageHelper.startPage(0, 10).doSelectPageInfo(() ->
                sysPermissionMapper.selectByExample(buildExample(param)));
        return pageInfo;
    }

    private SysPermissionExample buildExample(SysPermission record) {
        SysPermissionExample example = new SysPermissionExample();
        SysPermissionExample.Criteria criteria = example.createCriteria();

        if(Objects.isNull(record) && StringUtils.isNotEmpty(record.getName())) {
            criteria.andNameEqualTo(record.getName());
        }
        criteria.andDelFlagEqualTo(DelFlagEnum.N.getCode());
        example.setOrderByClause("create_time desc");

        return example;
    }
}
