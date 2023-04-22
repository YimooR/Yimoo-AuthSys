package top.yimoorua.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.yimoorua.model.system.SysDept;
import top.yimoorua.model.vo.SysDeptQueryVo;

/**
* @author haoyang
* @description 针对表【sys_dept(组织机构)】的数据库操作Mapper
* @createDate 2023-04-13 22:23:55
* @Entity top.yimoorua.system.domain.SysDept
*/
@Mapper
@Repository
public interface SysDeptMapper extends BaseMapper<SysDept> {
    IPage<SysDept> selectPage(Page<SysDept> sysDeptPage, @Param("vo") SysDeptQueryVo sysDeptQueryVo);
}




