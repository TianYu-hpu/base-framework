package cn.com.emindsoft.entity.base;


import lombok.Data;

import java.io.Serializable;

/**
 * 分页VO
 * @author tianyu
 */
@Data
public class PageBaseEntity implements Serializable {

    private static final long serialVersionUID = -4570683491229932381L;

    /**
     * 分页大小
     */
    private Integer pageSize;
    /**
     * 第几页
     */
    private Integer pageNo;
    /**
     * 总共多少条记录
     */
    private Long total;
    /**
     * 总共多少页
     */
    private Integer pages;
}
