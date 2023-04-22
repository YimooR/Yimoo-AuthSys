package top.yimoorua.system.annotation;

import top.yimoorua.system.enums.BusinessType;
import top.yimoorua.system.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @ClassName Log
 * @Description 日志注解接口
 * @date 2023/4/11 22:22
 * @Version 1.0
 * @Author hao yang
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) //作用范围
@Documented
public @interface Log {
    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseData() default true;
}
