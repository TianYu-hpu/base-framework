package cn.com.zenmaster.controller;

import cn.com.zenmaster.entity.po.SysPermission;
import cn.com.zenmaster.enums.ResponseCodeEnum;
import cn.com.zenmaster.service.SysPermissionService;
import cn.com.zenmaster.util.ResponseUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("系统权限接口")
@RequestMapping("/sys/permission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @ApiOperation("系统权限保存")
    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody SysPermission record) {
        int rowCount = sysPermissionService.save(record);
        if(1 == rowCount) {
            return ResponseUtil.success(ResponseCodeEnum.CREATE_SUCCESS);
        }
        return ResponseUtil.success(ResponseCodeEnum.CREATE_FAIL);
    }

    @ApiOperation("更新系统权限接口")
    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody SysPermission record) {
        int rowCount = sysPermissionService.update(record);
        if(1 == rowCount) {
            return ResponseUtil.success(ResponseCodeEnum.CREATE_SUCCESS);
        }
        return ResponseUtil.success(ResponseCodeEnum.CREATE_FAIL);
    }

    @ApiOperation("根据id查找系统权限")
    @GetMapping("/{id}")
    public Map<String,Object> id(@PathVariable String id) {
        SysPermission result = sysPermissionService.findByPrimaryKey(id);
        return ResponseUtil.success(result);
    }

    @ApiOperation("根据条件查找系统权限")
    @PostMapping("/list")
    public Map<String,Object> list(@RequestBody SysPermission record) {
        List<SysPermission> sysPermissionList = sysPermissionService.findByExample(record);
        return ResponseUtil.success(sysPermissionList);
    }

    @ApiOperation("分页查找系统权限")
    @PostMapping("/page")
    public Map<String,Object> page(@RequestBody SysPermission record) {
        PageInfo<SysPermission> pageInfo = sysPermissionService.findPageByExample(record);
        return ResponseUtil.success(pageInfo);
    }
}
