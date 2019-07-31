package cn.com.emindsoft.entity.vo;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @Auther: tianyu
 * @Date: 2019/5/17 22:55
 * @Description:
 */
@Data
public class UserVo {

    /**
     * 昵称
     */
    private String nickName;
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
    private String password;
    private Integer passwordLength;
    /**
     * 盐
     */
    private String salt;


}
