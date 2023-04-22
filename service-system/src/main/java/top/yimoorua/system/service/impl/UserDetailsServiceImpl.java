package top.yimoorua.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.yimoorua.model.system.SysUser;
import top.yimoorua.system.custom.CustomUser;
import top.yimoorua.system.service.SysMenuService;
import top.yimoorua.system.service.SysUserService;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserDetailsServiceImpl
 * @Description TODO
 * @date 2023/4/10 22:18
 * @Version 1.0
 * @Author hao yang
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser =sysUserService.getUserInfoByUsername(username);
        if(sysUser==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        if(sysUser.getStatus().intValue()==0){
            throw new RuntimeException("用户被禁用了！");
        }
        //根据userId查询操作权限数据
        List<String> userPermsList = sysMenuService.getUserButtonList(sysUser.getId());
        //转换成security要求的格式数据
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        for(String perm:userPermsList){
            authorities.add(new SimpleGrantedAuthority(perm.trim()));
        }
        return new CustomUser(sysUser, authorities);
    }
}
