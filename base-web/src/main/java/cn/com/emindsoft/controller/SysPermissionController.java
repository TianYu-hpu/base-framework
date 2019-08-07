package cn.com.emindsoft.controller;

import cn.com.emindsoft.entity.po.SysPermission;
import cn.com.emindsoft.enums.ResponseCodeEnum;
import cn.com.emindsoft.service.SysPermissionService;
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
@Controller
@ResponseBody
@RefreshScope
@RequestMapping("/sys/permission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody SysPermission record) {
        int rowCount = sysPermissionService.save(record);
        if(1 == rowCount) {
            return ResponseUtil.success(ResponseCodeEnum.CREATE_SUCCESS);
        }
        return ResponseUtil.success(ResponseCodeEnum.CREATE_FAIL);
    }

    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody SysPermission record) {
        int rowCount = sysPermissionService.update(record);
        if(1 == rowCount) {
            return ResponseUtil.success(ResponseCodeEnum.CREATE_SUCCESS);
        }
        return ResponseUtil.success(ResponseCodeEnum.CREATE_FAIL);
    }

    @GetMapping("/{id}")
    public Map<String,Object> id(@PathVariable String id) {
        SysPermission result = sysPermissionService.findByPrimaryKey(id);
        return ResponseUtil.success(result);
    }

    @PostMapping("/list")
    public Map<String,Object> list(@RequestBody SysPermission record) {
        List<SysPermission> sysPermissionList = sysPermissionService.findByExample(record);
        return ResponseUtil.success(sysPermissionList);
    }

    @PostMapping("/page")
    public Map<String,Object> page(@RequestBody SysPermission record) {
        PageInfo<SysPermission> pageInfo = sysPermissionService.findPageByExample(record);
        return ResponseUtil.success(pageInfo);
    }
}
