package cn.com.emindsoft.entity.vo.request;

import cn.com.emindsoft.entity.base.PageBaseEntity;
import lombok.Data;

/**
 * @author tianyu
 * @Auther: tianyu
 * @Date: 2019/5/17 22:55
 * @Description:
 */
@Data
public class UserQueryVo {

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

    private PageBaseEntity page;

}
