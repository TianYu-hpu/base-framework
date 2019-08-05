package cn.com.emindsoft.service;


import java.util.List;
import java.util.Map;

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
    Map<String, Object> save(T t);

    /**
     * 更新，必须有主键
     * @param t
     * @return
     */
    Map<String, Object> update(T t);


    /**
     * 查询列表
     * @param param
     * @return
     */
    List<T> findByExample(T param);

}
