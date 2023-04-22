package top.yimoorua.system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.yimoorua.model.system.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.yimoorua.model.vo.SysUserQueryVo;

/**
* @author haoyang
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2023-03-28 21:44:45
* @Entity top.yimoorua.model.system.SysUser
*/
public interface SysUserMapper extends BaseMapper<SysUser> {
       IPage<SysUser> selectPage(Page<SysUser> sysUserPage, @Param("vo") SysUserQueryVo sysUserQueryVo);
}




