package cn.com.zenmaster.enums;

/**
 * @Author: tianyu
 * @Date: 2019/5/18 10:58
 * @Description: 菜单类型
 */
public enum MenuTypeEnum implements BaseCodeEnum<MenuTypeEnum, String>{

    DIRECTORy("0", "目录"),
    MENU("1", "菜单"),
    BUTTON("1", "按钮"),
    STATIC("2", "静态资源");

    private String code;
    private String desc;

    MenuTypeEnum(String code, String desc) {
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
