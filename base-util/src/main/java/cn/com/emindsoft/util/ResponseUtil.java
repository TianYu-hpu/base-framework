package cn.com.emindsoft.util;

import cn.com.emindsoft.exception.ExceptionEnum;

import java.util.HashMap;
import java.util.List;
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
        return response(0, code, message);
    }

    /**
     * 失败返回
     * @param code
     * @param message
     * @return
     */
    public static Map<String, Object> fail(String code, String message) {
        return response(1, code, message);
    }

    /**
     * 失败返回
     * @param exceptionEnum
     * @return
     */
    public static Map<String, Object> fail(ExceptionEnum exceptionEnum) {
        return response(1, exceptionEnum.getCode(), exceptionEnum.getDesc());
    }

    private static Map<String, Object> response(int status, String code, String message) {
        return response(status, code, message ,null);
    }


    public static Map<String, Object> response(int status, String code, String message, List<Object> list) {
        Map<String, Object> response = new HashMap<>(10);
        Map<String, Object> data = new HashMap<>(10);
        response.put("status", status);
        data.put("code", code);
        data.put("message", message);
        data.put("list", list);
        response.put("data", data);
        return response;
    }

    public static Map<String, Object> response(int status, String code, String message, Object data) {
        Map<String, Object> response = new HashMap<>(10);
        Map<String, Object> result = new HashMap<>(10);
        response.put("status", status);
        result.put("code", code);
        result.put("message", message);
        result.put("item", data);
        response.put("data", result);
        return response;
    }

}
