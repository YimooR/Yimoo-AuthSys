package top.yimoorua.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yimoorua.model.system.SysRole;
import top.yimoorua.model.vo.AssginRoleVo;
import top.yimoorua.model.vo.SysRoleQueryVo;

import java.util.Map;

/**
 * @ClassName SysRoleService
 * @Description TODO
 * @date 2023/3/22 21:59
 * @Version 1.0
 * @Author hao yang
 */
public interface SysRoleService extends IService<SysRole> {
    /**
     * 条件分页查询
     */
    IPage<SysRole> selectPage(Page<SysRole> sysRolePage, SysRoleQueryVo sysRoleQueryVo);

    Map<String, Object> getRoleByUserId(String userId);

    void doAssign(AssginRoleVo assginRoleVo);
}
