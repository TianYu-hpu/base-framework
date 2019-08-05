package cn.com.emindsoft.service;

import cn.com.emindsoft.entity.po.SysPermission;

import java.util.List;
import java.util.Map;

/**
 * @author tianyu
 * @Date: 2019/5/17 23:02
 * @Description:
 */
public interface SysPermissionService extends BaseService<SysPermission>{

    /**
     * 保存
     * @param record
     * @return
     */
    @Override
    Map<String, Object> save(SysPermission record);

    /**
     * 更新，必须有主键
     * @param record
     * @return
     */
    @Override
    Map<String, Object> update(SysPermission record);


    /**
     * 主键查询
     * @param id
     * @return
     */
    SysPermission findByPrimaryKey(String id);

    /**
     * 查询列表
     * @param param
     * @return
     */
    @Override
    List<SysPermission> findByExample(SysPermission param);

}
