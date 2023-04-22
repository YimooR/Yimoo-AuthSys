package top.yimoorua.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.yimoorua.common.result.Result;
import top.yimoorua.common.utils.MD5;
import top.yimoorua.model.system.SysRole;
import top.yimoorua.model.system.SysUser;
import top.yimoorua.model.vo.SysUserQueryVo;
import top.yimoorua.system.annotation.Log;
import top.yimoorua.system.enums.BusinessType;
import top.yimoorua.system.service.SysUserService;

/**
 * @ClassName SysUserController
 * @Description TODO
 * @date 2023/3/28 21:49
 * @Version 1.0
 * @Author hao yang
 */
@RestController
@RequestMapping("/admin/system/sysUser")
@Api(tags="用户管理接口")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 条件分页查询接口
     *
     * @param page           当前页
     * @param limit          行数
     * @param sysUserQueryVo
     * @return
     */
    @ApiOperation("用户条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result findQueryUser(@PathVariable Long page,
                                @PathVariable Long limit,
                                SysUserQueryVo sysUserQueryVo
    ) {
        //创建page对象
        Page<SysUser> sysUserPage = new Page<>(page, limit);
        //调用service方法
        IPage<SysUser> sysUserIPage = sysUserService.selectPage(sysUserPage, sysUserQueryVo);
        return Result.ok(sysUserIPage);
    }

    /**
     * @param sysUser
     * @return
     * @RequestBody 不能使用get提交方式
     * 传递json格式数据，把json格式数据封装到对象里面
     */
    @Log(title="用户管理",businessType = BusinessType.INSERT)
    @ApiOperation("添加用户接口")
    @PostMapping("save")
    public Result save(@RequestBody SysUser sysUser) {
        //把输入的密码用MD5进行加密
        String encrypt=MD5.encrypt(sysUser.getPassword());
        sysUser.setPassword(encrypt);
        sysUser.setStatus(1);//默认为1
        sysUser.setHeadUrl("https://blog.yimoorua.top/static/img/avator.gif?imageView2/1/w/80/h/80");
        boolean flag = sysUserService.save(sysUser);
        if (flag) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 通过id查询用户接口
     * @param id
     * @return
     */
    @ApiOperation("id查询接口")
    @PostMapping("findUserById/{id}")
    public Result findUserById(@PathVariable Long id) {
        SysUser sysUser = sysUserService.getById(id);
        return Result.ok(sysUser);
    }

    /**
     * 修改用户接口
     * @param sysUser
     * @return
     */
    @Log(title = "用户管理",businessType = BusinessType.UPDATE)
    @ApiOperation("修改用户接口")
    @PostMapping("update")
    public Result updateUser(@RequestBody SysUser sysUser) {
        boolean flag = sysUserService.updateById(sysUser);
        if (flag) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 逻辑删除接口
     * @param id
     * @return
     */
    @Log(title = "用户管理",businessType = BusinessType.DELETE)
    @ApiOperation("逻辑删除用户接口")
    @DeleteMapping("remove/{id}")
    public Result delete(@PathVariable Long id) {
        boolean flag = sysUserService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @Log(title="用户管理",businessType = BusinessType.STATUS)
    @ApiOperation("更改用户状态的接口")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id,
                               @PathVariable Integer status
                               ){
        sysUserService.updateStatus(id,status);
        return Result.ok();
    }
}
