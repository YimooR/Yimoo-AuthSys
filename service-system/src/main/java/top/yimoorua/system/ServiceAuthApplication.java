package top.yimoorua.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName ServiceAuthApplication
 * @Description TODO
 * @date 2023/2/21 22:18
 * @Version 1.0
 * @Author hao yang
 */

@SpringBootApplication   //SpringBoot启动类
@MapperScan("top.yimoorua.system.mapper") //指定mapper扫描的包
public class ServiceAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class,args);
    }
}

