package cn.com.emindsoft.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 配置常量
 *
 * @author tianyu
 */
@Configuration
@ConfigurationProperties
public class ConfigConstant {

    /**
     * session 用户名
     */
    public static final String SESSION_USERNAME = "session_username";

    /**
     * 系统级别登录用户
     */
    public static final String SESSION_SYS_USERNAME = "session_sys_username";

    @Value("${web.staticFile")
    public String webStaticFile;

    public String getWebStaticFile() {
        return webStaticFile;
    }

    public void setWebStaticFile(String webStaticFile) {
        this.webStaticFile = webStaticFile;
    }

    public static final String WEB_STATIC_FILES = ".css,.js,.png,.jpg,.gif,.jpeg,.bmp,.ico,.swf,.psd,.htc,.crx,.xpi,.exe,.ipa,.apk";

    public static final String URL_SUFFIX = ".html";
}
