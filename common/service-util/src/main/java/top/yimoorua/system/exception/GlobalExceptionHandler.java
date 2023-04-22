package top.yimoorua.system.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yimoorua.common.result.Result;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 全局异常处理
 * @date 2023/3/23 23:02
 * @Version 1.0
 * @Author hao yang
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail().message("执行了全局异常处理");
    }

    /**
     * 特定异常处理，优先执行特定异常处理
     * @param a
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException a){
        a.printStackTrace();
        return Result.fail().message("执行了算数异常处理");
    }

    /**
     * 自定义异常处理
     * @param y 自定义异常类
     * @return
     */
    @ExceptionHandler(YimooruaException.class)
    @ResponseBody
    public Result error(YimooruaException y){
        y.printStackTrace();
        return Result.fail().code(y.getCode()).message(y.getMsg());
    }
}
