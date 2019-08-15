package cn.com.emindsoft.controller;

import cn.com.emindsoft.entity.mapstruct.UserMap;
import cn.com.emindsoft.entity.po.User;
import cn.com.emindsoft.entity.vo.request.UserAddVo;
import cn.com.emindsoft.enums.ResponseCodeEnum;
import cn.com.emindsoft.service.UserService;
import cn.com.emindsoft.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @author admin
 * @date 2019-05-14
 * @description controller
 */
@RestController
@ResponseBody
@RefreshScope
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Map<String,Object> save(@RequestBody UserAddVo userAddVo) {
        User user = UserMap.INSTANCE.addVoToPo(userAddVo);
        int rowCount = userService.save(user);
        if(1 == rowCount) {
            return ResponseUtil.success(ResponseCodeEnum.REGISTER_SUCCESS);
        }
        return ResponseUtil.success(ResponseCodeEnum.REGISTER_FAIL);
    }
}
