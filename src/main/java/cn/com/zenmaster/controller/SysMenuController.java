package cn.com.zenmaster.controller;

import cn.com.zenmaster.entity.po.SysMenu;
import cn.com.zenmaster.enums.ResponseCodeEnum;
import cn.com.zenmaster.service.SysMenuService;
import cn.com.zenmaster.utils.ResponseUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
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
@RequestMapping("/sys/menu")
@Api("系统菜单接口")
public class SysMenuController {

    @Autowired
    private SysMenuService userService;

    @ApiOperation("添加菜单")
    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody SysMenu record) {
        int rowCount = userService.save(record);
        if(1 == rowCount) {
            return ResponseUtil.success(ResponseCodeEnum.CREATE_SUCCESS);
        }
        return ResponseUtil.success(ResponseCodeEnum.CREATE_FAIL);
    }

    @ApiOperation("更新菜单")
    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody SysMenu record) {
        int rowCount = userService.update(record);
        if(1 == rowCount) {
            return ResponseUtil.success(ResponseCodeEnum.UPDATE_SUCCESS);
        }
        return ResponseUtil.success(ResponseCodeEnum.UPDATE_FAIL);
    }

    @ApiOperation("根据id查找指定才dna")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单id", required = true, paramType = "path")
    })
    @GetMapping("/{id}")
    public Map<String,Object> id(@PathVariable String id) {
        SysMenu result = userService.findByPrimaryKey(id);
        return ResponseUtil.success(result);
    }

    @ApiOperation("菜单列表")
    @PostMapping("/list")
    public Map<String,Object> list(@RequestBody SysMenu record) {
        List<SysMenu> sysMenuList = userService.findByExample(record);
        return ResponseUtil.success(sysMenuList);
    }

    @ApiOperation("菜单分页查找")
    @PostMapping("/page")
    public Map<String,Object> page(@RequestBody SysMenu record) {
        PageInfo<SysMenu> pageInfo = userService.findPageByExample(record);
        return ResponseUtil.success(pageInfo);
    }


}
