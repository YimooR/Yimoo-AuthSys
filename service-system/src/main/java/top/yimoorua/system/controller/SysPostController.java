package top.yimoorua.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import top.yimoorua.common.result.Result;
import top.yimoorua.common.utils.MD5;
import top.yimoorua.model.system.SysPost;
import top.yimoorua.model.system.SysUser;
import top.yimoorua.system.annotation.Log;
import top.yimoorua.system.enums.BusinessType;
import top.yimoorua.system.service.SysDeptService;
import top.yimoorua.system.service.SysPostService;

/**
 * @ClassName SysPostController
 * @Description TODO
 * @date 2023/4/18 22:52
 * @Version 1.0
 * @Author hao yang
 */
@Api(tags = "岗位管理接口")
@RestController
@RequestMapping("/admin/system/sysPost")
public class SysPostController {
    @Autowired
    private SysPostService sysPostService;

    @Log(title="岗位管理",businessType = BusinessType.OTHER)
    @ApiOperation("分页列表接口")
    @GetMapping("{page}/{limit}")
    public Result getPageList(
                              @PathVariable Long page,
                              @PathVariable Long limit
                              ){
        Page<SysPost> postPage =new Page<>(page,limit);
        IPage<SysPost> postIPage=sysPostService.page(postPage);
        return Result.ok(postIPage);
    }

    @Log(title="岗位管理",businessType = BusinessType.STATUS)
    @ApiOperation("更改岗位状态的接口")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id,
                               @PathVariable Integer status
    ){
        sysPostService.updateStatus(id,status);
        return Result.ok();
    }

    @Log(title="岗位管理",businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除岗位")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean flag = sysPostService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @Log(title="岗位管理",businessType = BusinessType.INSERT)
    @ApiOperation("添加岗位接口")
    @PostMapping("save")
    public Result save(@RequestBody SysPost sysPost) {
        boolean flag = sysPostService.save(sysPost);
        if (flag) {
            return Result.ok();
        }
        return Result.fail();
    }

    /**
     * 通过id查询部门接口
     * @param id
     * @return
     */
    @ApiOperation("id查询接口")
    @PostMapping("findPostById/{id}")
    public Result findPostById(@PathVariable Long id) {
        SysPost sysPost = sysPostService.getById(id);
        return Result.ok(sysPost);
    }

    /**
     * 修改岗位接口
     * @param sysPost
     * @return
     */
    @Log(title = "岗位管理",businessType = BusinessType.UPDATE)
    @ApiOperation("修改岗位接口")
    @PostMapping("update")
    public Result updatePost(@RequestBody SysPost sysPost) {
        boolean flag = sysPostService.updateById(sysPost);
        if (flag) {
            return Result.ok();
        }
        return Result.fail();
    }
}
