package top.yimoorua.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.yimoorua.model.system.SysOperLog;
import top.yimoorua.model.vo.SysOperLogQueryVo;

/**
 * @ClassName OperLogService
 * @Description TODO
 * @date 2023/4/11 22:26
 * @Version 1.0
 * @Author hao yang
 */

public interface OperLogService {

    /**
     * 保存操作日志
     * @param sysOperLog
     */
    void saveSysLog(SysOperLog sysOperLog);

    /**
     * 操作日志分页查询
     * @param page
     * @param limit
     * @param sysOperLogQueryVo
     * @return
     */
    IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo);
}
