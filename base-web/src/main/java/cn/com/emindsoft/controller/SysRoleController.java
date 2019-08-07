package cn.com.emindsoft.controller;

import cn.com.emindsoft.entity.po.SysRole;
import cn.com.emindsoft.enums.ResponseCodeEnum;
import cn.com.emindsoft.service.SysRoleService;
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
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody SysRole record) {
        int rowCount = sysRoleService.save(record);
        if(1 == rowCount) {
            return ResponseUtil.success(ResponseCodeEnum.CREATE_SUCCESS);
        }
        return ResponseUtil.success(ResponseCodeEnum.CREATE_FAIL);
    }

    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody SysRole record) {
        int rowCount = sysRoleService.update(record);
        if(1 == rowCount) {
            return ResponseUtil.success(ResponseCodeEnum.CREATE_SUCCESS);
        }
        return ResponseUtil.success(ResponseCodeEnum.CREATE_FAIL);
    }

    @GetMapping("/{id}")
    public Map<String,Object> id(@PathVariable String id) {
        SysRole result = sysRoleService.findByPrimaryKey(id);
        return ResponseUtil.success(result);
    }

    @PostMapping("/list")
    public Map<String,Object> list(@RequestBody SysRole record) {
        List<SysRole> sysRoleList = sysRoleService.findByExample(record);
        return ResponseUtil.success(sysRoleList);
    }

    @PostMapping("/page")
    public Map<String,Object> page(@RequestBody SysRole record) {
        PageInfo<SysRole> pageInfo = sysRoleService.findPageByExample(record);
        return ResponseUtil.success(pageInfo);
    }


}
