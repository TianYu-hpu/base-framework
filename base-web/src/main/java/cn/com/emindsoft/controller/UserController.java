package cn.com.emindsoft.controller;

import cn.com.emindsoft.entity.po.User;
import cn.com.emindsoft.enums.ResponseCodeEnum;
import cn.com.emindsoft.service.UserService;
import cn.com.emindsoft.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * @author admin
 * @date 2019-05-14
 * @description controller
 */
@Controller
@ResponseBody
@RefreshScope
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public Map<String,Object> register(User user) {
        User result = userService.findByUserName(user.getUsername());
        if(result != null) {
            ResponseUtil.fail(ResponseCodeEnum.ACCOUNT_ALREADY_EXISTS);
        }
        userService.save(user);
        return ResponseUtil.success(ResponseCodeEnum.REGISTER_SUCCESS);
    }
}
