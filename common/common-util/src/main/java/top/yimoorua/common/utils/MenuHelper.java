package top.yimoorua.common.utils;

import top.yimoorua.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MenuHelper
 * @Description 菜单树形结构工具类
 * @date 2023/4/1 22:45
 * @Version 1.0
 * @Author hao yang
 */
public class MenuHelper {

    /**
     * 构建树形结构
     * @param sysMenuList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        //创建集合封装最终的数据
        List<SysMenu> trees=new ArrayList<>();
        //遍历所有菜单的集合
        for(SysMenu sysMenu:sysMenuList){
            //找到递归的入口，parentId=0
            if(sysMenu.getParentId().longValue()==0){
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    /**
     * 从根节点进行递归查询，查询子接口
     * 判断id==parentId,true为子节点进行封装
     * @param sysMenu
     * @param sysMenuList
     * @return
     */
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        //数据初始化
        sysMenu.setChildren(new ArrayList<SysMenu>());

        //遍历递归查找
        for(SysMenu it:sysMenuList){
            //获取当前菜单id
            String id=sysMenu.getId();
            //获取所有菜单的parentId
            //比对
            if(Long.parseLong(id)==it.getParentId()) {
                if(sysMenu.getChildren()==null){
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it, sysMenuList));
            }
        }
        return sysMenu;
    }


}
