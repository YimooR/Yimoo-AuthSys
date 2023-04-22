package top.yimoorua.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.yimoorua.model.system.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author haoyang
* @description 针对表【sys_role_menu(角色菜单)】的数据库操作Mapper
* @createDate 2023-04-02 13:54:54
* @Entity top.yimoorua.system.domain.SysRoleMenu
*/
@Repository
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

}




