package cn.com.zenmaster.entity.po;

import cn.com.zenmaster.entity.base.BaseEntity;
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