package top.yimoorua.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.yimoorua.model.system.SysLoginLog;
import top.yimoorua.model.vo.SysLoginLogQueryVo;

/**
 * @ClassName LoginLogService
 * @Description TODO
 * @date 2023/4/11 21:45
 * @Version 1.0
 * @Author hao yang
 */
public interface LoginLogService {
    //登录日志
    public void recordLoginLog(String username,Integer status,
                               String ipaddr,String message);

    //条件分页查询登录日志
    IPage<SysLoginLog> selectPage(long page, long limit, SysLoginLogQueryVo sysLoginLogQueryVo);
}
