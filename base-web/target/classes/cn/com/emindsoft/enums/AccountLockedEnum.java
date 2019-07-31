package cn.com.emindsoft.enums;

/**
 * @Author: tianyu
 * @Date: 2019/5/18 10:58
 * @Description: 账户是否锁定
 */
public enum AccountLockedEnum implements BaseCodeEnum<AccountLockedEnum, String>{

    LOCKED("Y", "账户已锁定"),
    UNLOCKED("N", "账户未锁定");

    private String code;
    private String desc;

    AccountLockedEnum(String code, String desc) {
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
