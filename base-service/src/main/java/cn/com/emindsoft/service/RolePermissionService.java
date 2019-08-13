package cn.com.emindsoft.service;

import cn.com.emindsoft.entity.po.RolePermission;

import java.util.List;

/**
 * @author tianyu
 * @Date: 2019/5/17 23:02
 * @Description:
 */
public interface RolePermissionService extends BaseService<RolePermission>{

    /**
     * 保存
     * @param record
     * @return
     */
    @Override
    int save(RolePermission record);

    /**
     * 更新，必须有主键
     * @param record
     * @return
     */
    @Override
    int update(RolePermission record);

    /**
     * 查询列表
     * @param param
     * @return
     */
    @Override
    List<RolePermission> findByExample(RolePermission param);

}
