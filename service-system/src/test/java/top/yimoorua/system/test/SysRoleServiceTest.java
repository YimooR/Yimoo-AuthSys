package top.yimoorua.system.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.yimoorua.model.system.SysRole;
import top.yimoorua.system.service.SysRoleService;

import java.util.List;

/**
 * @ClassName SysRoleServiceTest
 * @Description TODO
 * @date 2023/3/22 22:06
 * @Version 1.0
 * @Author hao yang
 */
@SpringBootTest
public class SysRoleServiceTest {
    @Autowired
    private SysRoleService sysRoleService;

    //利用service层，查询所有数据
    @Test
    public void list(){
        List<SysRole> sysRoles = sysRoleService.list();
        System.out.println(sysRoles);
    }

    //添加数据
    @Test
    public void add(){
        SysRole sysRole=new SysRole();
        sysRole.setRoleCode("test");
        sysRole.setRoleName("Yimooruaa");
        sysRole.setDescription("使用Service层添加数据");
        sysRoleService.save(sysRole);
    }

    //修改数据
    @Test
    public void update(){
        SysRole sysRole=sysRoleService.getById(2);
        sysRole.setDescription("service层修改数据");
        sysRoleService.updateById(sysRole);
    }


}
