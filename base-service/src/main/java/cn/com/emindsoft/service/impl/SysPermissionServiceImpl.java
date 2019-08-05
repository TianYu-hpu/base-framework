package cn.com.emindsoft.service.impl;

import cn.com.emindsoft.entity.po.SysPermission;
import cn.com.emindsoft.entity.po.SysPermissionExample;
import cn.com.emindsoft.enums.ResponseCodeEnum;
import cn.com.emindsoft.mapper.SysPermissionMapper;
import cn.com.emindsoft.service.SysPermissionService;
import cn.com.emindsoft.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> save(SysPermission record) {
        record.preInsertOrUpdate();
        sysPermissionMapper.insert(record);
        return ResponseUtil.success(ResponseCodeEnum.CREATE_SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> update(SysPermission record) {
        record.preInsertOrUpdate();
        sysPermissionMapper.updateByExample(record, buildExample(record));
        return ResponseUtil.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    @Override
    public SysPermission findByPrimaryKey(String id) {
        return sysPermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysPermission> findByExample(SysPermission param) {
        return sysPermissionMapper.selectByExample(buildExample(param));
    }

    private SysPermissionExample buildExample(SysPermission record) {
        SysPermissionExample example = new SysPermissionExample();
        SysPermissionExample.Criteria criteria = example.createCriteria();

        if(Objects.isNull(record) && StringUtils.isNotEmpty(record.getName())) {
            criteria.andNameEqualTo(record.getName());
        }

        example.setOrderByClause("create_time desc");

        return example;
    }
}
