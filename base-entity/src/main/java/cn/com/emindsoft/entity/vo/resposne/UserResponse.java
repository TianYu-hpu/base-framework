package cn.com.emindsoft.entity.vo.resposne;

import lombok.Data;

import java.util.Date;

/**
 * @author tianyu
 * @Auther: tianyu
 * @Date: 2019/5/17 22:55
 * @Description:
 */
@Data
public class UserResponse {

    private String id;

    /**
     * 登录用户名
     */
    private String username;
    /**
     * 电话号
     */
    private String phone;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;

    private String activeFlag;

    private String delFlag;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;


}
