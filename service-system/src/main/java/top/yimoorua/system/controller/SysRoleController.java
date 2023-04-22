package top.yimoorua.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import top.yimoorua.common.result.Result;
import top.yimoorua.model.system.SysRole;
import top.yimoorua.model.vo.AssginRoleVo;
import top.yimoorua.model.vo.SysRoleQueryVo;
import top.yimoorua.system.annotation.Log;
import top.yimoorua.system.enums.BusinessType;
import top.yimoorua.system.service.SysRoleService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SysRoleController
 * @Description TODO
 * @date 2023/3/22 23:15
 * @Version 1.0
 * @Author hao yang
 */
@RestController
/**
 * 接口访问的一部分路径
 */
@RequestMapping("/admin/system/sysRole")
@Api(tags = "角色管理接口")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 查询所有的记录
     * 访问路径：localhost:8800/admin/system/sysRole/findAll
     * @return
     */
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("查询所有角色接口")
    @GetMapping("findAll")
    public Result findAll() {
        List<SysRole> sysRoles = sysRoleService.list();
        return Result.ok(sysRoles);
    }

    /**
     * 逻辑删除接口
     * 访问路径：localhost:8800/admin/system/sysRole/delete/{id}
     *
     * @param id
     * @return
     */
    @Log(title = "角色管理",businessType = BusinessType.DELETE)
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("逻辑删除角色接口")
    @DeleteMapping("remove/{id}")
    public Result delete(@PathVariable Long id) {
        boolean flag = sysRoleService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    /**
     * 条件分页查询接口
     *
     * @param page           当前页
     * @param limit          行数
     * @param sysRoleQueryVo
     * @return
     */
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result findQueryRole(@PathVariable Long page,
                                @PathVariable Long limit,
                                SysRoleQueryVo sysRoleQueryVo
    ) {
        //创建page对象
        Page<SysRole> sysRolePage = new Page<>(page, limit);
        //调用service方法
        IPage<SysRole> sysRoleIPage = sysRoleService.selectPage(sysRolePage, sysRoleQueryVo);
        return Result.ok(sysRoleIPage);
    }

    /**
     * @param sysRole
     * @return
     * @RequestBody 不能使用get提交方式
     * 传递json格式数据，把json格式数据封装到对象里面
     */
    @Log(title = "角色管理",businessType = BusinessType.INSERT)
    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @ApiOperation("添加角色接口")
    @PostMapping("save")
    public Result save(@RequestBody SysRole sysRole) {
        boolean flag = sysRoleService.save(sysRole);
        if (flag) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 通过id查询角色接口
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("id查询接口")
    @PostMapping("findRoleById/{id}")
    public Result findRoleById(@PathVariable Long id) {
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    /**
     * 修改角色接口
     *
     * @param sysRole
     * @return
     */
    @Log(title = "角色管理",businessType = BusinessType.UPDATE)
    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @ApiOperation("修改角色接口")
    @PostMapping("update")
    public Result updateRole(@RequestBody SysRole sysRole) {
        boolean flag = sysRoleService.updateById(sysRole);
        if (flag) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 通过id批量删除角色接口
     *
     * @param ids 通过请求体中获取id数组
     * @return
     */
    @Log(title = "角色管理",businessType = BusinessType.DELETE)
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("批量删除接口")
    @DeleteMapping("batchRemove")
    public Result batchRemoveRole(@RequestBody List<Long> ids) {
        boolean flag = sysRoleService.removeByIds(ids);
//        try{
//            int a=1/0;
//        }catch (Exception e){
//            throw new YimooruaException(20001,"执行了自定义异常");
//        }
        if (flag) {
            return Result.ok();
        }
        return Result.fail();
    }


    @ApiOperation("根据用户获取角色数据")
    @GetMapping("toAssign/{userId}")
    public Result toAssign(@PathVariable String userId){
        Map<String,Object> roleMap=sysRoleService.getRoleByUserId(userId);
        return Result.ok(roleMap);
    }

    @Log(title = "角色管理",businessType = BusinessType.ASSGIN)
    @ApiOperation("用户重新分配角色")
    @PostMapping("doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo){
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }
}
