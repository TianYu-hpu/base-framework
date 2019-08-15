package cn.com.emindsoft.service;


import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author: tianyu
 * @Date: 2019/5/17 23:02
 * @Description:
 */
public interface BaseService<T> {

    /**
     * 保存
     * @param t
     * @return
     */
    int save(T t);

    /**
     * 更新，必须有主键
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 根据主键查找
     * @param id
     * @return
     */
    T findByPrimaryKey(String id);

    /**
     * 根据主键删除
     * @param id
     */
    void deleteByPrimaryKey(String id);

    /**
     * 查询列表
     * @param param
     * @return
     */
    List<T> findByExample(T param);

    /**
     * 分页查找
      * @param param
     * @return
     */
    PageInfo<T> findPageByExample(T param);

}
