package cn.com.zenmaster.controller;

import cn.com.zenmaster.entity.mapstruct.UserMap;
import cn.com.zenmaster.entity.po.User;
import cn.com.zenmaster.entity.vo.request.UserQueryVo;
import cn.com.zenmaster.entity.vo.resposne.UserResponse;
import cn.com.zenmaster.enums.ResponseCodeEnum;
import cn.com.zenmaster.service.UserService;
import cn.com.zenmaster.util.ResponseUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RefreshScope
@Api("用户操作接口")
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @ApiOperation("查找用户列表")
    @PostMapping("/list")
    public Map<String,Object> page(@RequestBody UserQueryVo user) {
        PageInfo<User> pageinfo = userService.findPageByExample(UserMap.INSTANCE.voToPo(user));
        List<User> pageList = pageinfo.getList();
        List<UserResponse> list = UserMap.INSTANCE.poListToVoList(pageList);
        return ResponseUtil.success(pageinfo, list);
    }

    @ApiOperation("根据id查找指定用户")
    @GetMapping("/{id}")
    public Map<String,Object> findByPrimaryKey(@PathVariable String id) {
        User result = userService.findByPrimaryKey(id);
        UserResponse response = UserMap.INSTANCE.poToUserResponse(result);
        return ResponseUtil.success(response);
    }

    @ApiOperation("根据id删除用户")
    @GetMapping("/del/{id}")
    public Map<String,Object> deleteByPrimaryKey(@PathVariable String id) {
        userService.deleteByPrimaryKey(id);
        return ResponseUtil.success();
    }

    @ApiOperation("编辑用户信息")
    @PostMapping("/edit")
    public Map<String,Object> updateByPrimaryKey(@RequestBody User user) {
        int rowCount = userService.updateByPrimaryKey(user);
        if(1 == rowCount) {
            return ResponseUtil.success(ResponseCodeEnum.SUCCESS);
        }
        return ResponseUtil.success(ResponseCodeEnum.FAIL);
    }



}
