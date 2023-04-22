package top.yimoorua.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.yimoorua.common.result.Result;
import top.yimoorua.model.system.SysDept;
import top.yimoorua.system.annotation.Log;
import top.yimoorua.system.enums.BusinessType;
import top.yimoorua.system.service.SysDeptService;

import java.util.List;

/**
 * @ClassName SysDeptController
 * @Description 部门管理
 * @date 2023/4/13 22:27
 * @Version 1.0
 * @Author hao yang
 */
@Api(tags = "部门管理接口")
@RestController
@RequestMapping(value="/admin/system/sysDept")
@CrossOrigin
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @Log(title = "部门管理",businessType = BusinessType.OTHER)
    @ApiOperation("获取部门列表")
    @GetMapping("findNodes")
    public Result findNodes(){
        List<SysDept> sysDeptList=sysDeptService.findNodes();
        return Result.ok(sysDeptList);
    }

    @Log(title = "部门管理",businessType = BusinessType.INSERT)
    @ApiOperation("添加部门")
    @PostMapping("/save")
    public Result save(@RequestBody SysDept sysDept){
        sysDeptService.save(sysDept);
       return Result.ok();
    }

    @Log(title = "部门管理",businessType = BusinessType.UPDATE)
    @ApiOperation("修改部门")
    @PostMapping("/update")
    public Result update(@RequestBody SysDept sysDept){
        sysDeptService.updateById(sysDept);
        return Result.ok();
    }

    @Log(title = "部门管理",businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除部门")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean flag = sysDeptService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @Log(title = "部门管理",businessType = BusinessType.STATUS)
    @ApiOperation("更改部门状态的接口")
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable String id,
                               @PathVariable Integer status
    ){
        sysDeptService.updateStatus(id,status);
        return Result.ok();
    }
}
