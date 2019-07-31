package cn.com.emindsoft.exception;

import cn.com.emindsoft.enums.BaseCodeEnum;

/**
 * 异常枚举
 * code：错误码
 * desc:错误描述
 *
 * @author tianyu
 */
public enum ExceptionEnum implements BaseCodeEnum<ExceptionEnum, String>{

    SUCCESS("00000", "成功"),
    FAIL("99999", "失败"),
    // 系统类异常 01
    SYS_ERR("01001", "系统异常，请稍后重试"),
    ARG_ERR("01002", "参数错误"),
    USER_GET_ERR("01003", "用户信息获取失败"),
    USER_HAVE_NO_PERMISSION("01004", "无操作权限"),
    UPLOAD_FILE_SIZE_TOO_LARGE("99002", "上传文件大小超过限制"),
    UNSUPPORTED_OPR_ERR("99001", "不支持的操作"),
    PARAM_IS_EMPTY("07001","请求参数对象不能为空"),
    DATABASE_OPERATION_FAILE("00002","数据库操作异常"),
    REQUESTNO_EXIST("00001", "同一来源的付款请求号不可重复");

    private String code;
    private String desc;


    ExceptionEnum(String code, String desc) {
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