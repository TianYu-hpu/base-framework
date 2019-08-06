package cn.com.emindsoft.service;

import cn.com.emindsoft.entity.po.SysMenu;
import com.github.pagehelper.PageInfo;

import java.util.List;

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
    int save(SysMenu record);

    /**
     * 更新，必须有主键
     * @param record
     * @return
     */
    @Override
    int update(SysMenu record);

    /**
     * 主键查询
     * @param id
     * @return
     */
    @Override
    SysMenu findByPrimaryKey(String id);

    /**
     * 查询列表
     * @param param
     * @return
     */
    @Override
    List<SysMenu> findByExample(SysMenu param);

    /**
     * 分页
     * @param param
     * @return
     */
    @Override
    PageInfo<SysMenu> findPageByExample(SysMenu param);

}
