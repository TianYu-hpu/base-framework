package cn.com.zenmaster.config;

import cn.com.zenmaster.entity.po.User;
import cn.com.zenmaster.service.UserService;
import cn.com.zenmaster.utils.JwtUtil;
import cn.com.zenmaster.utils.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tianyu
 */
public class JwtFilter extends AccessControlFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    @Autowired
    private UserService userService;
    /**
     * 因为跨域发送过来的请求是首先是options请求
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETe");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        //跨域首先会发送一个option请求，这里给option请求直接返回正常状态
        if(httpServletRequest.getMethod().equalsIgnoreCase(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }

        return super.preHandle(request, response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //从请求头中获取header token
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.isEmpty(token)) {
            loginError(servletResponse, "token已过期");
        } else {
            try {
                String userId = JwtUtil.getUserIdFromToken(token);
                User loginUser = userService.findByPrimaryKey(userId);
                JwtUtil.verifyToken(loginUser.getSalt(), token);
                return true;
            } catch (Exception e) {
                loginError(servletResponse, "token已过期");
            }
        }

        return false;
    }


    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) {
        return false;
    }

    private void loginError(ServletResponse servletResponse, String message) {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            response.sendError(401, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
