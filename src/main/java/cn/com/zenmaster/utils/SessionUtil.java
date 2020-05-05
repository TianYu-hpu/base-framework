package cn.com.zenmaster.utils;

import cn.com.zenmaster.entity.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 设置当前登录用户
 *
 * @author tianyu
 */
public class SessionUtil {

    private static final Logger log = LoggerFactory.getLogger(SessionUtil.class);

    /**
     * session设置当前登录用户的用户名
     * @param user
     * @param request
     */
    public static void setCurrentUser(HttpServletRequest request, User user) {
        setAttribute(request, ConfigConstant.SESSION_USERNAME, user.getUsername());
        log.info("当前登录用户 用户名:[{}] 登录时间:[{}]", user.getUsername(), DateUtils.formatNowToFullFormat());
    }

    /**
     * 获取当前登录用户的用户名
     * @param request
     * @return username and sessionId
     */
    public static String getCurrentUser(HttpServletRequest request) {
        String username = (String) getAttribute(request, ConfigConstant.SESSION_USERNAME);
        String sessionId = getSessionId(request);
        StringBuffer sb = new StringBuffer();
        sb.append("username=");
        sb.append(username);
        sb.append(",sessionId=");
        sb.append(sessionId);
        return sb.toString();
    }

    /**
     * 获取当前登录用户的用户名
     * @param request
     * @return username
     */
    public static String getUsernameOfCurrentUser(HttpServletRequest request) {
        String username = (String) getAttribute(request, ConfigConstant.SESSION_USERNAME);
        String sessionId = getSessionId(request);
        StringBuffer sb = new StringBuffer();
        sb.append("username=");
        sb.append(username);
        return sb.toString();
    }

    /**
     * 获取当前登录用户的用户名
     * @param request
     * @return Session
     */
    public static HttpSession getSession(HttpServletRequest request) {
            return request.getSession();
    }

    /**
     * 获取当前登录用户的用户名
     * @param request
     * @return SessionId
     */
    public static String getSessionId(HttpServletRequest request) {
        return getSession(request).getId();
    }



    /**
     * 设置 attribute
     * @param request
     * @param key
     * @param value
     *
     */
    public static void setAttribute(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    /**
     * 设置 attribute
     * @param request
     * @param key
     * @return Object
     */
    public static Object getAttribute(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();
        Object value = session.getAttribute(key);
        return value;
    }

    /**
     * 注销登录
     * @param request
     * @return Object
     */
    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }






}
