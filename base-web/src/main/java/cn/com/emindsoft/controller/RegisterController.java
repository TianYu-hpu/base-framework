package cn.com.emindsoft.controller;

import cn.com.emindsoft.entity.po.User;
import cn.com.emindsoft.enums.ResponseCodeEnum;
import cn.com.emindsoft.service.UserService;
import cn.com.emindsoft.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Map<String,Object> save(@RequestBody User user) {
        int rowCount = userService.save(user);
        if(1 == rowCount) {
            return ResponseUtil.success(ResponseCodeEnum.REGISTER_SUCCESS);
        }
        return ResponseUtil.success(ResponseCodeEnum.REGISTER_FAIL);
    }
}
