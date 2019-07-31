package cn.com.emindsoft.enums;

/**
 * @Author: tianyu
 * @Date: 2019/5/18 10:58
 * @Description: 账户是否删除
 */
public enum DelFlagEnum implements BaseCodeEnum<DelFlagEnum, String>{

    Y("Y", "已删除"),
    N("N", "未删除");

    private String code;
    private String desc;

    DelFlagEnum(String code, String desc) {
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
