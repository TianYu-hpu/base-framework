package cn.com.emindsoft.service.impl;

import cn.com.emindsoft.entity.po.SysMenu;
import cn.com.emindsoft.entity.po.SysMenuExample;
import cn.com.emindsoft.enums.ResponseCodeEnum;
import cn.com.emindsoft.mapper.SysMenuMapper;
import cn.com.emindsoft.service.SysMenuService;
import cn.com.emindsoft.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author tianyu
 * @Date: 2019/5/17 23:07
 * @Description:
 */
@Service
@Slf4j
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper SysMenuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> save(SysMenu record) {
        record.preInsertOrUpdate();
        SysMenuMapper.insert(record);
        return ResponseUtil.success(ResponseCodeEnum.CREATE_SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> update(SysMenu record) {
        record.preInsertOrUpdate();
        SysMenuMapper.updateByExample(record, buildExample(record));
        return ResponseUtil.success(ResponseCodeEnum.UPDATE_SUCCESS);
    }

    @Override
    public SysMenu findByPrimaryKey(String id) {
        return SysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysMenu> findByExample(SysMenu param) {
        return SysMenuMapper.selectByExample(buildExample(param));
    }

    private SysMenuExample buildExample(SysMenu record) {
        SysMenuExample example = new SysMenuExample();
        SysMenuExample.Criteria criteria = example.createCriteria();

        example.setOrderByClause("create_time desc");

        return example;
    }
}
