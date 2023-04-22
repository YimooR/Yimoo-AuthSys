package top.yimoorua.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.yimoorua.model.system.SysRole;
import top.yimoorua.model.vo.SysRoleQueryVo;

/**
 * @ClassName SysRoleMapper
 * @Description TODO
 * @date 2023/2/21 22:34
 * @Version 1.0
 * @Author hao yang
 */

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 条件分页查询
     * @param sysRolePage
     * @param sysRoleQueryVo
     * @return
     */
     IPage<SysRole> selectPage(Page<SysRole> sysRolePage,@Param("vo") SysRoleQueryVo sysRoleQueryVo);
}
