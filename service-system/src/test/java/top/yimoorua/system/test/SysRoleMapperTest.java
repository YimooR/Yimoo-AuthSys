package top.yimoorua.system.test;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.yimoorua.model.system.SysRole;
import top.yimoorua.system.mapper.SysRoleMapper;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName SysRoleMapperTest
 * @Description TODO
 * @date 2023/2/21 22:36
 * @Version 1.0
 * @Author hao yang
 */

@SpringBootTest
public class SysRoleMapperTest {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    //查询所有记录
    @Test
    public void all(){
        List<SysRole> sysRoleList=sysRoleMapper.selectList(null);
        sysRoleList.forEach(System.out::println);
    }

    //添加记录
    @Test
    public void add(){
        SysRole sysRole=new SysRole();
        sysRole.setRoleName("测试管理员");
        sysRole.setRoleCode("test");
        sysRole.setDescription("测试");
        int raw=sysRoleMapper.insert(sysRole);
        System.out.println(raw);
    }

    //修改操作
    @Test
    public void update(){
        //1.根据id查询
        SysRole sysRole=sysRoleMapper.selectById(11);
        //2.设置修改值
        sysRole.setDescription("YimooRua系统管理员");
        //3.提交修改
        sysRoleMapper.updateById(sysRole);
    }

    //id删除，逻辑删除
    @Test
    public void delete(){
        int rows=sysRoleMapper.deleteById(12);
        System.out.println(rows);
    }

    //批量删除
    @Test
    public void testBatchDelete(){
        sysRoleMapper.deleteBatchIds(Arrays.asList(1,2,8,9,10));
    }

    //条件查询
    @Test
    public void select(){
        //创建条件构造器对象
        QueryWrapper<SysRole> sysRoleQueryWrapper=new QueryWrapper<>();
        sysRoleQueryWrapper.like("role_name","管理员");
        List<SysRole> sysRoleList=sysRoleMapper.selectList(sysRoleQueryWrapper);
        sysRoleList.forEach(System.out::println);
    }

    //条件删除
    @Test
    public void wrapperDelete(){
        QueryWrapper<SysRole> sysRoleQueryWrapper =new QueryWrapper<>();
        sysRoleQueryWrapper.eq("role_code","test");
        sysRoleMapper.delete(sysRoleQueryWrapper);
    }
}
