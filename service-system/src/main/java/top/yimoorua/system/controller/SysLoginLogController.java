package top.yimoorua.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yimoorua.common.result.Result;
import top.yimoorua.model.system.SysLoginLog;
import top.yimoorua.model.vo.SysLoginLogQueryVo;
import top.yimoorua.system.service.LoginLogService;

/**
 * @ClassName SysLoginLogController
 * @Description TODO
 * @date 2023/4/11 21:59
 * @Version 1.0
 * @Author hao yang
 */
@Api(value = "SysLoginLog管理", tags = "SysLoginLog管理")
@RestController
@RequestMapping(value="/admin/system/sysLoginLog")
public class SysLoginLogController {
    @Autowired
    private LoginLogService loginLogService;

    /**
     * 条件分页查询登录日志
     * @param page
     * @param limit
     * @param sysLoginLogQueryVo
     * @return
     */
    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable long page,
                        @PathVariable long limit,
                        SysLoginLogQueryVo sysLoginLogQueryVo) {
        IPage<SysLoginLog> pageModel =  loginLogService.selectPage(page,limit,sysLoginLogQueryVo);
        return Result.ok(pageModel);
    }
}
