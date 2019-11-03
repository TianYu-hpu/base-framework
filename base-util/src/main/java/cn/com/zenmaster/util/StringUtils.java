package cn.com.zenmaster.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author tianyu
 */
@Component
@Slf4j
public class StringUtils extends org.apache.commons.lang3.StringUtils {


    private static String[] staticFiles =  StringUtils.split(ConfigConstant.WEB_STATIC_FILES, ",");

    /**
     * 判断访问URI是否是静态文件请求
     * @throws Exception
     */
    public static boolean isStaticFile(String uri) {
        if (staticFiles == null){
            try {
                throw new Exception("检测到没有配置静态文件后缀 .css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.endsWithAny(uri, staticFiles) && !StringUtils.endsWithAny(uri, ConfigConstant.URL_SUFFIX)
                && !StringUtils.endsWithAny(uri, ".jsp") && !StringUtils.endsWithAny(uri, ".java")){
            return true;
        }
        return false;
    }

    /**
     * bytes to String
     * @param bytes
     * @return
     */
    public static String toString(byte[] bytes) {
        return new String(bytes);
    }

    /**
     * String to bytes
     * @param object
     * @return
     */
    public static byte[] getBytes(String object) {
        if(object == null) {
            return null;
        } else {
            return object.getBytes();
        }
    }

    /**
     * 生成随机字符串
     * @param length
     * @return
     */
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成盐
     * @return salt
     */
    public static String generateSalt(){
        return getRandomString(16);
    }

}
