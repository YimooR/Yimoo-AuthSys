package top.yimoorua.system.service;

import top.yimoorua.model.system.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yimoorua.model.vo.AssginMenuVo;
import top.yimoorua.model.vo.RouterVo;

import java.util.List;

/**
* @author haoyang
* @description 针对表【sys_menu(菜单表)】的数据库操作Service
* @createDate 2023-04-01 11:32:59
*/
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findNodes();

    void removeMenuById(String id);

    List<SysMenu> findMenuByRoleId(String roleId);

    void doAssign(AssginMenuVo assginMenuVo);

    List<RouterVo> getUserMenuList(String id);

    List<String> getUserButtonList(String id);
}
