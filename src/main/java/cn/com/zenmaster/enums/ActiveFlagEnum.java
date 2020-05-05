package cn.com.zenmaster.enums;

/**
 * @Author: tianyu
 * @Date: 2019/5/18 10:58
 * @Description: 账户是否激活
 */
public enum ActiveFlagEnum implements BaseCodeEnum<ActiveFlagEnum, String>{

    ACTIVE("Y", "账户已激活"),
    UNACTIVE("N", "账户未激活");

    private String code;
    private String desc;

    ActiveFlagEnum(String code, String desc) {
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
