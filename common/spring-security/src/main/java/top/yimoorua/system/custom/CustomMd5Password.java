package top.yimoorua.system.custom;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import top.yimoorua.common.utils.MD5;

/**
 * @ClassName CustomMD5Password
 * @Description 自定义MD5密码加密组件
 * @date 2023/4/10 21:59
 * @Version 1.0
 * @Author hao yang
 */
@Component
public class CustomMd5Password implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return MD5.encrypt(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5.encrypt(rawPassword.toString()));
    }
}
