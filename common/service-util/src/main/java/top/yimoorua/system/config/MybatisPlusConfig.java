package top.yimoorua.system.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MybatisPlusConfig
 * @Description 分页插件
 * @date 2023/3/23 21:23
 * @Version 1.0
 * @Author hao yang
 */
@EnableTransactionManagement
@Configuration
@MapperScan("top.yimoorua.system.mapper")
public class MybatisPlusConfig {
    /**
     * 分页插件
     * @return
     */
    @Bean
    public MybatisPlusInterceptor addPaginationInnerInterceptor(){
        MybatisPlusInterceptor interceptor=new MybatisPlusInterceptor();
        //向Mybatis过滤器中添加分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
