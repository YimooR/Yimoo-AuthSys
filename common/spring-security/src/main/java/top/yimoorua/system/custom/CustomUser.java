package top.yimoorua.system.custom;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import top.yimoorua.model.system.SysUser;

import java.util.Collection;

/**
 * @ClassName CustomUser
 * @Description 添加CustomUser对象
 * @date 2023/4/10 22:04
 * @Version 1.0
 * @Author hao yang
 */
public class CustomUser extends User {

    /**
     * 我们自己的用户实体对象，要调取用户信息时直接获取这个实体对象
     */
    private SysUser sysUser;

    public CustomUser(SysUser sysUser, Collection<? extends GrantedAuthority> authorities) {
        super(sysUser.getUsername(), sysUser.getPassword(), authorities);
        this.sysUser = sysUser;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
