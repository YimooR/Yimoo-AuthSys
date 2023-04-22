package top.yimoorua.system.mapper;

import org.apache.ibatis.annotations.Param;
import top.yimoorua.model.system.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author haoyang
* @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
* @createDate 2023-04-01 11:32:59
* @Entity top.yimoorua.system.domain.SysMenu
*/
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> findMenuListUserId(@Param("userId") String id);
}




