package cn.com.emindsoft.entity.vo.request;


import lombok.Data;

import java.io.Serializable;

/**
 * 分页VO
 * @author tianyu
 */
@Data
public class PageVO implements Serializable {

    private static final long serialVersionUID = -4570683491229932381L;

    private Integer pageSize;

    private Integer pageNo;
}
