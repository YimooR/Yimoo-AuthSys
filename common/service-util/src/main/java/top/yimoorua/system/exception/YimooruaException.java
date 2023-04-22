package top.yimoorua.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName YimooRuaException
 * @Description TODO
 * @date 2023/3/23 23:26
 * @Version 1.0
 * @Author hao yang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YimooruaException extends RuntimeException{

    private Integer code;
    private String msg;
}
