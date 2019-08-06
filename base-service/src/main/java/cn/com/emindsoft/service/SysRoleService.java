package cn.com.emindsoft.service;

import cn.com.emindsoft.entity.po.SysRole;

import java.util.List;

/**
 * @author tianyu
 * @Date: 2019/5/17 23:02
 * @Description:
 */
public interface SysRoleService extends BaseService<SysRole>{

    /**
     * 保存
     * @param record
     * @return
     */
    @Override
    int save(SysRole record);

    /**
     * 更新，必须有主键
     * @param record
     * @return
     */
    @Override
    int update(SysRole record);


    /**
     * 主键查询
     * @param id
     * @return
     */
    SysRole findByPrimaryKey(String id);
    /**
     * 查询列表
     * @param param
     * @return
     */
    @Override
    List<SysRole> findByExample(SysRole param);

}
