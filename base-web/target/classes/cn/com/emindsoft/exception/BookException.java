package cn.com.emindsoft.exception;

/**
 * 业务抛出的异常给前台展示用
 *
 * @author tianyu
 */
public class BookException extends RuntimeException {

    private String code;

    public BookException(String message, Throwable cause) {
        super(message, cause);
        this.code = ExceptionEnum.SYS_ERR.getCode();
    }

    public BookException(Throwable cause) {
        super(ExceptionEnum.SYS_ERR.getDesc(), cause);
        this.code = ExceptionEnum.SYS_ERR.getCode();
    }

    public BookException(ExceptionEnum errEnum) {
        this(errEnum.getCode(), errEnum.getDesc());
    }

    public BookException(ExceptionEnum errEnum, String message) {
        this(errEnum.getCode(), message);
    }

    public BookException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
