package cn.com.emindsoft.service;

import cn.com.emindsoft.entity.po.SysMenu;

import java.util.List;
import java.util.Map;

/**
 * @author tianyu
 * @Date: 2019/5/17 23:02
 * @Description:
 */
public interface SysMenuService extends BaseService<SysMenu>{

    /**
     * 保存
     * @param record
     * @return
     */
    @Override
    Map<String, Object> save(SysMenu record);

    /**
     * 更新，必须有主键
     * @param record
     * @return
     */
    @Override
    Map<String, Object> update(SysMenu record);


    /**
     * 主键查询
     * @param id
     * @return
     */
    SysMenu findByPrimaryKey(String id);
    /**
     * 查询列表
     * @param param
     * @return
     */
    @Override
    List<SysMenu> findByExample(SysMenu param);

}
