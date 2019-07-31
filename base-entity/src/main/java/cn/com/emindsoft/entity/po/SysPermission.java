package cn.com.emindsoft.entity.po;

import lombok.Data;

import java.util.Date;

/**
 * @author tianyu
 */
@Data
public class SysPermission extends BaseEntity {
    private String id;

    private String name;

    private String resourceType;

    private String url;

    private String permission;

    private String parentId;

    private String roleId;

    private String delFlag;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;

}