package top.yimoorua.system.controller;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.yimoorua.common.result.Result;
import top.yimoorua.common.utils.JwtHelper;
import top.yimoorua.common.utils.MD5;
import top.yimoorua.model.system.SysUser;
import top.yimoorua.model.vo.LoginVo;
import top.yimoorua.system.exception.YimooruaException;
import top.yimoorua.system.service.SysUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName IndexController
 * @Description 用户登录接口控制器
 * @date 2023/3/26 22:34
 * @Version 1.0
 * @Author hao yang
 */
@Api(tags="用户登录接口")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录接口
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo){
        //根据用户名称进行查询数据库
        SysUser sysUser =sysUserService.getUserInfoByUsername(loginVo.getUsername());
        System.out.println(sysUser);
        //查询为空
        if(null==sysUser){
            throw new YimooruaException(20001,"用户不存在！");
        }
        //密码是否一致
        if(!MD5.encrypt(loginVo.getPassword()).equals(sysUser.getPassword())){
            throw new YimooruaException(20001,"密码错误！");
        }
        //判断用户是否可用
        if(sysUser.getStatus().intValue()==0){
            throw new YimooruaException(20001,"该账号已被禁用！联系管理员！");
        }
        System.out.println("ok");
        //根据userid和username生产token，通过map返回
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
        return Result.ok(map);
    }

    /**
     * 登录信息接口
     * {"code":20000,
     * "data":{
     * "roles":["admin"],
     * "introduction":"I am a super administrator",
     * "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
     * "name":"Super Admin"
     *   }
     * }
     * @return
     */
    @GetMapping("info")
    public Result info(HttpServletRequest httpServletRequest){
        //获取请求头token字符串
        String token=httpServletRequest.getHeader("token");
        //获取用户名
        String username=JwtHelper.getUsername(token);
        //根据用户名获取用户信息
        Map<String,Object> map=sysUserService.getUserInfo(username);
        return Result.ok(map);
    }

    /**
     * 退出接口
     * @return
     */
    @PostMapping("logout")
    public Result logout(){
        return Result.ok();
    }
}
