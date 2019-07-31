package cn.com.emindsoft.service;

import cn.com.emindsoft.entity.po.UserRole;

import java.util.List;

/**
 * @author tianyu
 * @Date: 2019/5/17 23:02
 * @Description:
 */
public interface UserRoleService extends BaseService<UserRole>{

    /**
     * 保存
     * @param record
     * @return
     */
    @Override
    int save(UserRole record);

    /**
     * 更新，必须有主键
     * @param record
     * @return
     */
    @Override
    int update(UserRole record);

    /**
     * 查询列表
     * @param param
     * @return
     */
    @Override
    List<UserRole> findByExample(UserRole param);

}
