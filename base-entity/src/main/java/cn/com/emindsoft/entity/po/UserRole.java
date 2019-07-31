package cn.com.emindsoft.entity.po;

import lombok.Data;

import java.util.Date;

/**
 * @author tianyu
 */
@Data
public class UserRole extends BaseEntity {
    private String id;

    private String userId;

    private String roleId;

    private String delFlag;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
}