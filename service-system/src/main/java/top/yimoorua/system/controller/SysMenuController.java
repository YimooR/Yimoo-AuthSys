package top.yimoorua.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.yimoorua.common.result.Result;
import top.yimoorua.model.system.SysMenu;
import top.yimoorua.model.vo.AssginMenuVo;
import top.yimoorua.system.service.SysMenuService;

import java.util.List;

/**
 * @ClassName SysMenuController
 * @Description TODO
 * @date 2023/4/1 11:35
 * @Version 1.0
 * @Author hao yang
 */
@RestController
@RequestMapping("/admin/system/sysMenu")
@Api(tags = "菜单管理接口")
public class SysMenuController {
     @Autowired
     private SysMenuService sysMenuService;

    /**
     * 菜单列表（树形）
     * @return
     */
    @ApiOperation("菜单列表")
    @GetMapping("findNodes")
    public Result findNodes(){
        List<SysMenu> sysMenuList=sysMenuService.findNodes();
        return Result.ok(sysMenuList);
    }

    @ApiOperation("添加菜单")
    @PostMapping("save")
    public Result save(@RequestBody SysMenu sysMenu){
        sysMenuService.save(sysMenu);
        return Result.ok();
    }

    @ApiOperation("根据ID查询")
    @GetMapping("findNode/{id}")
    public Result findNode(@PathVariable String id){
        SysMenu sysMenu=sysMenuService.getById(id);
        return  Result.ok(sysMenu);
    }

    @ApiOperation("修改菜单")
    @PostMapping("update")
    public Result update(@RequestBody SysMenu sysMenu){
        sysMenuService.updateById(sysMenu);
        return Result.ok();
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id){
        sysMenuService.removeMenuById(id);
        return Result.ok();
    }

    @ApiOperation("根据角色获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssign(@PathVariable String roleId){
        List<SysMenu> sysMenuList=sysMenuService.findMenuByRoleId(roleId);
        return Result.ok(sysMenuList);
    }

    @ApiOperation("给角色分配菜单权限")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuVo assginMenuVo){
         sysMenuService.doAssign(assginMenuVo);
         return Result.ok();
    }
}
