package cn.com.emindsoft.util;

import cn.com.emindsoft.enums.ResponseCodeEnum;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.formula.functions.T;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tianyu
 * @Date: 2019/5/18 11:12
 * @Description: 给前端返回response
 */
public class ResponseUtil {

    /**
     * 成功返回
     * @param code
     * @param message
     * @return
     */
    public static Map<String, Object> success(String code, String message) {
        return response(ConfigConstant.STATUS_SUCCESS, code, message);
    }

    /**
     * 成功返回
     * @param responseCodeEnum
     * @return
     */
    public static Map<String, Object> success(ResponseCodeEnum responseCodeEnum) {
        return response(ConfigConstant.STATUS_SUCCESS, responseCodeEnum.getCode(), responseCodeEnum.getDesc());
    }

    /**
     * 成功返回
     * @param page
     * @return
     */
    public static Map<String, Object> success(PageInfo<T> page) {
        return response(ConfigConstant.STATUS_SUCCESS, ResponseCodeEnum.SUCCESS, page);
    }

    /**
     * 成功返回
     * @param data
     * @return
     */
    public static Map<String, Object> success(Object data) {
        return response(ConfigConstant.STATUS_SUCCESS, ResponseCodeEnum.SUCCESS, data);
    }



    /**
     * 失败返回
     * @param code
     * @param message
     * @return
     */
    public static Map<String, Object> fail(String code, String message) {
        return response(ConfigConstant.STATUS_FAIL, code, message);
    }

    /**
     * 失败返回
     * @param responseCodeEnum
     * @return
     */
    public static Map<String, Object> fail(ResponseCodeEnum responseCodeEnum) {
        return response(ConfigConstant.STATUS_FAIL, responseCodeEnum.getCode(), responseCodeEnum.getDesc());
    }

    private static Map<String, Object> response(int status, String code, String message) {
        return response(status, code, message ,"");
    }

    private static Map<String,Object> response(int status, String code, String message, Object data) {
        Map<String, Object> response = new HashMap<>(10);
        Map<String, Object> result = new HashMap<>(10);
        response.put("status", status);
        result.put("code", code);
        result.put("message", message);
        result.put("item", data);
        response.put("data", result);
        return response;
    }

    private static Map<String,Object> response(Integer statusSuccess, ResponseCodeEnum responseCodeEnum, PageInfo page) {
        Map<String, Object> response = new HashMap<>(10);
        Map<String, Object> data = new HashMap<>(10);
        response.put("status", statusSuccess);
        data.put("code", responseCodeEnum.getCode());
        data.put("message", responseCodeEnum.getDesc());
        data.put("page", page);
        response.put("data", data);
        return response;
    }

    private static Map<String,Object> response(Integer statusSuccess, ResponseCodeEnum responseCodeEnum, Object data) {
        Map<String, Object> response = new HashMap<>(10);
        Map<String, Object> result = new HashMap<>(10);
        response.put("status", statusSuccess);
        result.put("code", responseCodeEnum.getCode());
        result.put("message", responseCodeEnum.getDesc());
        result.put("item", data);
        response.put("data", result);
        return response;
    }

}
