package top.yimoorua.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yimoorua.model.system.SysRole;
import top.yimoorua.model.system.SysUserRole;
import top.yimoorua.model.vo.AssginRoleVo;
import top.yimoorua.model.vo.SysRoleQueryVo;
import top.yimoorua.system.mapper.SysRoleMapper;
import top.yimoorua.system.mapper.SysUserRoleMapper;
import top.yimoorua.system.service.SysRoleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SysRoleServiceImpl
 * @Description TODO
 * @date 2023/3/22 22:00
 * @Version 1.0
 * @Author hao yang
 */

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 条件分页查询
     * @param sysRolePage
     * @param sysRoleQueryVo
     * @return
     */
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> sysRolePage, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> sysRoleIPage=baseMapper.selectPage(sysRolePage,sysRoleQueryVo);
        return sysRoleIPage;
    }

    @Override
    public Map<String, Object> getRoleByUserId(String userId) {
        //获取所有的角色
        List<SysRole> roleList=baseMapper.selectList(null);
        //根据用户id查询
        QueryWrapper<SysUserRole> sysUserRoleQueryWrapper=new QueryWrapper<>();
        sysUserRoleQueryWrapper.eq("user_id",userId);
        List<SysUserRole> sysUserRoleList=sysUserRoleMapper.selectList(sysUserRoleQueryWrapper);
        //获取用户已经分配的角色id
        List<String> userRoleIds=new ArrayList<>();
        for(SysUserRole sysUserRole: sysUserRoleList){
            String roleId=sysUserRole.getRoleId();
            userRoleIds.add(roleId);
        }
        //创建返回的MAP
        Map<String,Object> returnMap=new HashMap<>();
        returnMap.put("allRoles",roleList);
        returnMap.put("userRoleIds",userRoleIds);
        return returnMap;
    }

    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //根据用户id删除之前分配的角色信息
        QueryWrapper<SysUserRole> sysUserRoleQueryWrapper=new QueryWrapper<>();
        sysUserRoleQueryWrapper.eq("user_id",assginRoleVo.getUserId());
        sysUserRoleMapper.delete(sysUserRoleQueryWrapper);
        //获取所有的角色id,添加到角色用户关系表
        List<String> roleIdList=assginRoleVo.getRoleIdList();
        for(String roleId:roleIdList){
            SysUserRole sysUserRole=new SysUserRole();
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRole.setRoleId(roleId);
            sysUserRoleMapper.insert(sysUserRole);
        }
    }


}
