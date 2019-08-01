package cn.com.emindsoft.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author tianyu
 */
@Component
@Slf4j
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    @Value("${web.staticFile}")
    private static String webStaticFile;
    @Value("${urlSuffix}")
    private static String urlSuffix;

    private static String[] staticFiles =  StringUtils.split(webStaticFile, ",");

    /**
     * 判断访问URI是否是静态文件请求
     * @throws Exception
     */
    public static boolean isStaticFile(String uri){
        if (staticFiles == null){
            try {
                throw new Exception("检测到“app.properties”中没有配置“web.staticFile”属性。配置示例：\n#静态文件后缀\n"
                        +"web.staticFile=.css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.endsWithAny(uri, staticFiles) && !StringUtils.endsWithAny(uri, urlSuffix)
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
