package cn.com.emindsoft.controller;

import cn.com.emindsoft.entity.po.SysMenu;
import cn.com.emindsoft.entity.po.User;
import cn.com.emindsoft.service.SysMenuService;
import cn.com.emindsoft.service.UserService;
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
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService userService;

    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody SysMenu record) {
        return userService.save(record);
    }
}
