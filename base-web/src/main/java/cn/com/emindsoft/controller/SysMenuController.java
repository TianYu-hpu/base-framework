package cn.com.emindsoft.controller;

import cn.com.emindsoft.entity.po.SysMenu;
import cn.com.emindsoft.enums.ResponseCodeEnum;
import cn.com.emindsoft.service.SysMenuService;
import cn.com.emindsoft.util.ResponseUtil;
import com.github.pagehelper.PageInfo;
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
@RestController
@ResponseBody
@RefreshScope
@RequestMapping("/sys/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService userService;

    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody SysMenu record) {
        int rowCount = userService.save(record);
        if(1 == rowCount) {
            return ResponseUtil.success(ResponseCodeEnum.CREATE_SUCCESS);
        }
        return ResponseUtil.success(ResponseCodeEnum.CREATE_FAIL);
    }

    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody SysMenu record) {
        int rowCount = userService.update(record);
        if(1 == rowCount) {
            return ResponseUtil.success(ResponseCodeEnum.UPDATE_SUCCESS);
        }
        return ResponseUtil.success(ResponseCodeEnum.UPDATE_FAIL);
    }

    @GetMapping("/{id}")
    public Map<String,Object> id(@PathVariable String id) {
        SysMenu result = userService.findByPrimaryKey(id);
        return ResponseUtil.success(result);
    }

    @PostMapping("/list")
    public Map<String,Object> list(@RequestBody SysMenu record) {
        List<SysMenu> sysMenuList = userService.findByExample(record);
        return ResponseUtil.success(sysMenuList);
    }

    @PostMapping("/page")
    public Map<String,Object> page(@RequestBody SysMenu record) {
        PageInfo<SysMenu> pageInfo = userService.findPageByExample(record);
        return ResponseUtil.success(pageInfo);
    }


}
