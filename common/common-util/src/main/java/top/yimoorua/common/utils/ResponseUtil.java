package top.yimoorua.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import top.yimoorua.common.result.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ResponseUtil
 * @Description 响应工具类
 * @date 2023/4/10 22:30
 * @Version 1.0
 * @Author hao yang
 */
public class ResponseUtil {
    public static void out(HttpServletResponse response, Result r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
