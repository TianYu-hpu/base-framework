package cn.com.emindsoft.controller;

import cn.com.emindsoft.entity.po.User;
import cn.com.emindsoft.enums.ResponseCodeEnum;
import cn.com.emindsoft.service.UserService;
import cn.com.emindsoft.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/list")
    public Map<String,Object> list(@RequestBody User user) {
        List<User> userList = userService.findByExample(user);
        return ResponseUtil.response(0, "200", "success", userList);
    }

    @GetMapping("/{id}")
    public Map<String,Object> id(@PathVariable String id) {
        User result = userService.findByPrimaryKey(id);
        return ResponseUtil.response(0, "200", "success", result);
    }

}
