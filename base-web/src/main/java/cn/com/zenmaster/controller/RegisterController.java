package cn.com.zenmaster.controller;

import cn.com.zenmaster.entity.mapstruct.UserMap;
import cn.com.zenmaster.entity.po.User;
import cn.com.zenmaster.entity.vo.request.UserAddVo;
import cn.com.zenmaster.enums.ResponseCodeEnum;
import cn.com.zenmaster.service.UserService;
import cn.com.zenmaster.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("注册接口")
public class RegisterController {

    @Autowired
    private UserService userService;

    @ApiOperation("注册")
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
