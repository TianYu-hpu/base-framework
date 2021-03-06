package cn.com.zenmaster.service.impl;

import cn.com.zenmaster.entity.po.SysMenu;
import cn.com.zenmaster.entity.po.SysMenuExample;
import cn.com.zenmaster.enums.DelFlagEnum;
import cn.com.zenmaster.mapper.SysMenuMapper;
import cn.com.zenmaster.service.SysMenuService;
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
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(SysMenu record) {
        record.preInsertOrUpdate();
        return sysMenuMapper.insert(record);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(SysMenu record) {
        record.preInsertOrUpdate();
        return sysMenuMapper.updateByExample(record, buildExample(record));
    }

    @Override
    public SysMenu findByPrimaryKey(String id) {
        SysMenu result = sysMenuMapper.selectByPrimaryKey(id);
        return result;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPrimaryKey(String id) {
        sysMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysMenu> findByExample(SysMenu param) {
        return sysMenuMapper.selectByExample(buildExample(param));
    }

    @Override
    public PageInfo<SysMenu> findPageByExample(SysMenu param) {
        PageInfo<SysMenu> pageInfo = PageHelper.startPage(0, 10).doSelectPageInfo(() ->
                sysMenuMapper.selectByExample(buildExample(param)));
        return pageInfo;
    }

    private SysMenuExample buildExample(SysMenu record) {
        SysMenuExample example = new SysMenuExample();
        SysMenuExample.Criteria criteria = example.createCriteria();

        criteria.andDelFlagEqualTo(DelFlagEnum.N.getCode());
        example.setOrderByClause("create_time desc");

        return example;
    }
}
