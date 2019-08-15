package cn.com.emindsoft.controller;

import cn.com.emindsoft.entity.mapstruct.UserMap;
import cn.com.emindsoft.entity.po.User;
import cn.com.emindsoft.entity.vo.request.UserVo;
import cn.com.emindsoft.entity.vo.resposne.UserResponse;
import cn.com.emindsoft.service.UserService;
import cn.com.emindsoft.util.ResponseUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @author admin
 * @date 2019-05-14
 * @description controller
 */
@RestController
@ResponseBody
@RefreshScope
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    public Map<String,Object> page(@RequestBody UserVo user) {
        PageInfo<User> pageinfo = userService.findPageByExample(UserMap.INSTANCE.voToPo(user));
        List<User> pageList = pageinfo.getList();
        List<UserResponse> list = UserMap.INSTANCE.poListToVoList(pageList);
        return ResponseUtil.success(pageinfo, list);
    }

    @GetMapping("/{id}")
    public Map<String,Object> id(@PathVariable String id) {
        User result = userService.findByPrimaryKey(id);
        UserResponse response = UserMap.INSTANCE.poToUserResponse(result);
        return ResponseUtil.success(response);
    }

    @GetMapping("/del/{id}")
    public Map<String,Object> delete(@PathVariable String id) {
        User result = userService.deleteByPrimaryKey(id);
        UserResponse response = UserMap.INSTANCE.poToUserResponse(result);
        return ResponseUtil.success(response);
    }



}
