package cn.com.zenmaster.controller;

import cn.com.zenmaster.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author admin
 * @date 2019-05-14
 * @description controller
 */
@RestController
@ResponseBody
@RefreshScope
@RequestMapping("")
@Api(value = "注销")
public class LogoutController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户退出登录")
    @ApiResponses({
            @ApiResponse(code = 200, message = "退出成功")
    })
    @GetMapping("/logout")
    public Map<String,Object> logout() {
        return userService.logout();
    }
}
