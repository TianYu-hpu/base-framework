package cn.com.zenmaster.enums;

/**
 * @Author: tianyu
 * @Date: 2019/5/18 10:58
 * @Description: 账户是否删除
 */
public enum PasswordExpiredEnum implements BaseCodeEnum<PasswordExpiredEnum, String>{

    Y("Y", "密码已过期"),
    N("N", "密码未过期");

    private String code;
    private String desc;

    PasswordExpiredEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
