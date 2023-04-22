package top.yimoorua.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.yimoorua.model.system.SysOperLog;

/**
 * @ClassName OperLogMapper
 * @Description
 * @date 2023/4/11 22:04
 * @Version 1.0
 * @Author hao yang
 */
@Repository
@Mapper
public interface OperLogMapper extends BaseMapper<SysOperLog> {
}
