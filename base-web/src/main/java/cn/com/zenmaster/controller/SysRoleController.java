package cn.com.zenmaster.controller;

import cn.com.zenmaster.entity.po.SysRole;
import cn.com.zenmaster.enums.ResponseCodeEnum;
import cn.com.zenmaster.service.SysRoleService;
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
@Api("角色接口")
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("保存接口")
    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody SysRole record) {
        int rowCount = sysRoleService.save(record);
        if(1 == rowCount) {
            return ResponseUtil.success(ResponseCodeEnum.CREATE_SUCCESS);
        }
        return ResponseUtil.success(ResponseCodeEnum.CREATE_FAIL);
    }

    @ApiOperation("更新角色")
    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody SysRole record) {
        int rowCount = sysRoleService.update(record);
        if(1 == rowCount) {
            return ResponseUtil.success(ResponseCodeEnum.CREATE_SUCCESS);
        }
        return ResponseUtil.success(ResponseCodeEnum.CREATE_FAIL);
    }

    @ApiOperation("根据id查找角色")
    @GetMapping("/{id}")
    public Map<String,Object> id(@PathVariable String id) {
        SysRole result = sysRoleService.findByPrimaryKey(id);
        return ResponseUtil.success(result);
    }

    @ApiOperation("根据条件查找角色列表")
    @PostMapping("/list")
    public Map<String,Object> list(@RequestBody SysRole record) {
        List<SysRole> sysRoleList = sysRoleService.findByExample(record);
        return ResponseUtil.success(sysRoleList);
    }

    @ApiOperation("根据条件查找角色分页")
    @PostMapping("/page")
    public Map<String,Object> page(@RequestBody SysRole record) {
        PageInfo<SysRole> pageInfo = sysRoleService.findPageByExample(record);
        return ResponseUtil.success(pageInfo);
    }


}
