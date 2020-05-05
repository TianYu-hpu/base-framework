package cn.com.zenmaster.entity.po;

import cn.com.zenmaster.entity.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class RolePermission extends BaseEntity {
    private String id;

    private String roleId;

    private String permissionId;

    private String delFlag;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

}