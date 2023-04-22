package top.yimoorua.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.yimoorua.model.system.SysRole;
import top.yimoorua.model.system.SysUser;
import top.yimoorua.model.vo.RouterVo;
import top.yimoorua.model.vo.SysUserQueryVo;
import top.yimoorua.system.service.SysMenuService;
import top.yimoorua.system.service.SysUserService;
import top.yimoorua.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author haoyang
* @description 针对表【sys_user(用户表)】的数据库操作Service实现
* @createDate 2023-03-28 21:44:45
*/
@Transactional
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public IPage<SysUser> selectPage(Page<SysUser> sysUserPage, SysUserQueryVo sysUserQueryVo) {
        return baseMapper.selectPage(sysUserPage,sysUserQueryVo);
    }

    @Override
    public void updateStatus(String id, Integer status) {
        //根据用户id查询数据
        SysUser sysUser=baseMapper.selectById(id);
        //设置状态
        sysUser.setStatus(status);
        //调用方法修改
        baseMapper.updateById(sysUser);
    }


    @Override
    public SysUser getUserInfoByUsername(String username) {
        QueryWrapper<SysUser> sysUserQueryWrapper=new QueryWrapper<>();
        sysUserQueryWrapper.eq("username",username);
        SysUser sysUser=baseMapper.selectOne(sysUserQueryWrapper);
        return sysUser;
    }

    @Override
    public Map<String, Object> getUserInfo(String username) {
        //根据用户名称查询用户基本信息
        SysUser sysUser=this.getUserInfoByUsername(username);
        //根据用户id查询菜单权限值
        List<RouterVo> menuList=sysMenuService.getUserMenuList(sysUser.getId());
        List<String> buttonList=sysMenuService.getUserButtonList(sysUser.getId());
        Map<String,Object> result=new HashMap<>();
        result.put("name",username);
        result.put("avatar","https://blog.yimoorua.top/static/img/avator.gif");
        result.put("roles","[\"admin\"]");
        //菜单的权限数据
        result.put("routers",menuList);
        result.put("buttons",buttonList);
        return result;
    }


}




