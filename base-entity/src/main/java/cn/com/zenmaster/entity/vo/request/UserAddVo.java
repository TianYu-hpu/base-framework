package cn.com.zenmaster.entity.vo.request;

import lombok.Data;


/**
 * @author tianyu
 * @Auther: tianyu
 * @Date: 2019/5/17 22:55
 * @Description:
 */
@Data
public class UserAddVo {

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
    /**
     * 密码
     */
    private String password;

}
