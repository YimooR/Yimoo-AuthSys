package top.yimoorua.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yimoorua.model.system.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yimoorua.model.vo.SysUserQueryVo;

import java.util.Map;

/**
* @author haoyang
* @description 针对表【sys_user(用户表)】的数据库操作Service
* @createDate 2023-03-28 21:44:45
*/
public interface SysUserService extends IService<SysUser> {
    //分页查询
    IPage<SysUser> selectPage(Page<SysUser> sysUserPage, SysUserQueryVo sysUserQueryVo);

    void updateStatus(String id, Integer status);

    //根据用户名称查询用户信息
    SysUser getUserInfoByUsername(String username);

    Map<String, Object> getUserInfo(String username);
}
