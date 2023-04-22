package top.yimoorua.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.yimoorua.model.system.SysLoginLog;

/**
 * @ClassName LoginLogMapper
 * @Description TODO
 * @date 2023/4/10 23:31
 * @Version 1.0
 * @Author hao yang
 */
@Repository
@Mapper
public interface LoginLogMapper extends BaseMapper<SysLoginLog> {

}
