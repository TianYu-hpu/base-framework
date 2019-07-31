package cn.com.emindsoft.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author admin
 * @date 2019-05-14
 * @description controller
 */
@Controller
@ResponseBody
@RefreshScope
@RequestMapping("/index")
public class HomeController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World";
    }
}
