package top.yimoorua.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.yimoorua.model.system.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author haoyang
* @description 针对表【sys_user_role(用户角色)】的数据库操作Mapper
* @createDate 2023-03-31 22:37:11
* @Entity top.yimoorua.system.domain.SysUserRole
*/
@Repository
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}




