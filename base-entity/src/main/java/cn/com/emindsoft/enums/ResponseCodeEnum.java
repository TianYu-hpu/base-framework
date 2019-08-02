package cn.com.emindsoft.enums;

/**
 * 响应码枚举
 * code：响应码
 * desc:描述
 *
 * @author tianyu
 */
public enum ResponseCodeEnum implements BaseCodeEnum<ResponseCodeEnum, String>{


    /***
     * 响应码和响应信息定义类
     * 1. 响应码定义规则为5为数字
     * 2. 前两位表示业务场景，最后三位表示状态。例如：100001。10:通用 001:系统未知异常
     * 3. 维护错误码后需要维护错误描述
     * 想响应码列表：
     *  10: 通用
     *      001：参数格式校验
     *  11：开户
     *  12: 交易
     *  13: 查询
     *  14: 银行
     *
     * @author yunpeng.zhao
     *
     */
    SUCCESS("00000", "成功"),
    FAIL("99999", "失败"),
    /**
     * 系统类 01
     */
    SYS_ERR("01001", "系统异常，请稍后重试"),
    ARG_ERR("01002", "参数错误"),
    USER_GET_ERR("01003", "用户信息获取失败"),
    USER_HAVE_NO_PERMISSION("01004", "无操作权限"),

    /**
     * 用户类02
     */
    LOGIN_FAILED("02001", "用户名或密码错误"),
    LOGIN_SUCCESS("02002", "登录成功"),

    ACCOUNT_ALREADY_EXISTS("02003", "账户已存在"),
    REGISTER_SUCCESS("02004", "注册成功"),
    LOGOUT_SUCCESS("02005", "注销成功"),
    ACCOUNT_NOT_EXISTS("02006", "账户不存在"),
    LOGIN_FAILED_MANY_TIMES("02007", "登录失败次数过多"),
    UPDATE_PASSWORD_SUCCESS("02008", "更新密码成功"),
    RESET_PASSWORD_SUCCESS("02009", "重置密码成功"),
    /**
     * 业务类 03
     */
    UPLOAD_FILE_SIZE_TOO_LARGE("03002", "上传文件大小超过限制"),
    UNSUPPORTED_OPR_ERR("03001", "不支持的操作"),
    PARAM_IS_EMPTY("03001","请求参数对象不能为空"),
    DATABASE_OPERATION_FAILE("03002","数据库操作异常"),


    /**
     * 财务类
     */
    REQUESTNO_EXIST("09001", "同一来源的付款请求号不可重复");
    private String code;
    private String desc;


    ResponseCodeEnum(String code, String desc) {
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