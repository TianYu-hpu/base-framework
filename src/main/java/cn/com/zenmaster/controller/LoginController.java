package cn.com.zenmaster.controller;

import cn.com.zenmaster.entity.po.User;
import cn.com.zenmaster.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
@Api(tags = "用户登录接口", value = "提供用户登录相关的Rest API")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value="用户登录", notes = "登录接口", response = HashMap.class, httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 200, message = "登录成功"),
            @ApiResponse(code = 404, message = "用户不存在")}
    )
    public Map<String,Object> login(@RequestBody @ApiParam("登录用户") User user) {
        return userService.login(user);
    }
}
