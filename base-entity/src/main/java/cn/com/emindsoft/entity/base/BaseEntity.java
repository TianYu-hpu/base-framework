package cn.com.emindsoft.entity.base;

import cn.com.emindsoft.entity.base.PageBaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: tianyu
 * @Date: 2019/5/17 22:31
 * @Description:
 */
@Data
public class BaseEntity implements Serializable {

    private String id;

    /**
     * Y:刪除
     * N:未刪除
     */
    private String delFlag;
    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private PageBaseEntity page;

    public void preInsertOrUpdate() {
        if(id == null) {
            preInsert();
        } else {
            preUpdate();
        }
    }


    private void preInsert() {
        setId(UUID.randomUUID().toString().replaceAll("-", ""));
        setCreateTime(new Date());
        setUpdateTime(new Date());
    }

    private void preUpdate() {
        setUpdateTime(new Date());
    }

}
