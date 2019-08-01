package cn.com.emindsoft.controller;

import cn.com.emindsoft.enums.ResponseCodeEnum;
import cn.com.emindsoft.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 全局异常处理
 * @author tianyu
 */
@ControllerAdvice
@Slf4j
public class ExceptionController {

    /**
     * 请求内容类型不支持异常处理
     * @param request
     * @param cause
     * @return
     */
    @ResponseBody
    @ExceptionHandler({HttpMediaTypeException.class, HttpRequestMethodNotSupportedException.class})
    public Object mediaTypeException(HttpServletRequest request, HttpMediaTypeException cause) {
        log.error("Case unexpected BookException" + request.getRequestURL(), cause);
        return ResponseUtil.fail(ResponseCodeEnum.UNSUPPORTED_OPR_ERR);
    }

    /**
     * 其它异常处理
     * @param request
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Object error(HttpServletRequest request, Throwable e) {
        log.error("Case unexpected system error" + request.getRequestURL(), e);
        return ResponseUtil.fail(ResponseCodeEnum.SYS_ERR.getCode(), e.getMessage());
    }
}
