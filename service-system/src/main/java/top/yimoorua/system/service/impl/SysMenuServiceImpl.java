package top.yimoorua.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import top.yimoorua.common.utils.MenuHelper;
import top.yimoorua.common.utils.RouterHelper;
import top.yimoorua.model.system.SysMenu;
import top.yimoorua.model.system.SysRole;
import top.yimoorua.model.system.SysRoleMenu;
import top.yimoorua.model.system.SysUserRole;
import top.yimoorua.model.vo.AssginMenuVo;
import top.yimoorua.model.vo.RouterVo;
import top.yimoorua.system.exception.YimooruaException;
import top.yimoorua.system.mapper.SysRoleMenuMapper;
import top.yimoorua.system.mapper.SysUserRoleMapper;
import top.yimoorua.system.service.SysMenuService;
import top.yimoorua.system.mapper.SysMenuMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author haoyang
* @description 针对表【sys_menu(菜单表)】的数据库操作Service实现
* @createDate 2023-04-01 11:32:59
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService{

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

   //菜单树形结构
    @Override
    public List<SysMenu> findNodes() {
        //获取所有的菜单
        List<SysMenu> sysMenuList=baseMapper.selectList(null);

        //把所有菜单返回要求的格式
        List<SysMenu> resultList= MenuHelper.buildTree(sysMenuList);
        return resultList;
    }

    @Override
    public void removeMenuById(String id) {
        //查询当前删除菜单下面是否子菜单
        //根据id=parentId
        QueryWrapper<SysMenu> wrapper=new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count=baseMapper.selectCount(wrapper);
        if(count>0){ //有子菜单
            throw new YimooruaException(201,"请先删除子菜单");
        }
        //调用删除
        baseMapper.deleteById(id);
    }

    //根据角色获取菜单
    @Override
    public List<SysMenu> findMenuByRoleId(String roleId) {
        //获取所有菜单 status=1
        QueryWrapper<SysMenu> sysMenuQueryWrapper=new QueryWrapper<>();
        sysMenuQueryWrapper.eq("status",1);
        List<SysMenu> sysMenuList=baseMapper.selectList(sysMenuQueryWrapper);
        //根据角色id查询角色分配过的菜单列表
        QueryWrapper<SysRoleMenu> sysRoleMenuQueryWrapper=new QueryWrapper<>();
        sysRoleMenuQueryWrapper.eq("role_id",roleId);
        List<SysRoleMenu> sysRoleMenus=sysRoleMenuMapper.selectList(sysRoleMenuQueryWrapper);
        //从第二步中，获取角色分配所有菜单id
        List<String> roleMenuIds=new ArrayList<>();
        for(SysRoleMenu sysRoleMenu:sysRoleMenus){
            String menuId=sysRoleMenu.getMenuId();
            roleMenuIds.add(menuId);
        }
        //数据处理：isSelect如果菜单选中true,否则false
        for(SysMenu sysMenu:sysMenuList){
            if(roleMenuIds.contains(sysMenu.getId())){
                //拿着分配菜单id和所有菜单进行比对，有相同，让isSelect值为true
                sysMenu.setSelect(true);
            }else {
                sysMenu.setSelect(false);
            }
        }
        //转换成一种树形结构
        List<SysMenu> sysMenus=MenuHelper.buildTree(sysMenuList);
        return sysMenus;
    }

    //给角色分配菜单权限
    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //根据角色id删除菜单权限
        QueryWrapper<SysRoleMenu> sysRoleQueryWrapper=new QueryWrapper<>();
        sysRoleQueryWrapper.eq("role_id",assginMenuVo.getRoleId());
        sysRoleMenuMapper.delete(sysRoleQueryWrapper);
        //遍历菜单id的列表。一个一个添加
        List<String> menuIdList=assginMenuVo.getMenuIdList();
        for(String menuId:menuIdList){
            SysRoleMenu sysRoleMenu=new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
            sysRoleMenuMapper.insert(sysRoleMenu);
        }
    }

    @Override
    public List<RouterVo> getUserMenuList(String id) {
        //1.admin是超级管理员，操作所有内容，判断是否为admin
        List<SysMenu> sysMenuList=null;
        //userid为1，为超级管理员
        if("1".equals(id)){
            QueryWrapper<SysMenu> sysMenuQueryWrapper=new QueryWrapper<>();
            sysMenuQueryWrapper.eq("status",1);
            sysMenuQueryWrapper.orderByAsc("sort_value");
            sysMenuList=baseMapper.selectList(sysMenuQueryWrapper);
        }else {
            //userId不为1，不是超级管理员
            sysMenuList=baseMapper.findMenuListUserId(id);
        }
        //构建成树形结构
        List<SysMenu> sysMenuTree=MenuHelper.buildTree(sysMenuList);
        //转换成前端路由要求格式数据
        List<RouterVo> routerVos = RouterHelper.buildRouters(sysMenuTree);
        return routerVos;
    }

    @Override
    public List<String> getUserButtonList(String id) {
        List<SysMenu> sysMenuList=null;
        //判断是否是管理员
        if("1".equals(id)){
            sysMenuList=baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status",1));
        }else {
            sysMenuList=baseMapper.findMenuListUserId(id);
        }
        //把菜单集合进行遍历，取得按钮
        List<String> permissionList=new ArrayList<>();
        for(SysMenu sysMenu:sysMenuList){
            if(sysMenu.getType()==2){
                permissionList.add(sysMenu.getPerms());
            }
        }
        return permissionList;
    }
}




