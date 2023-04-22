package top.yimoorua.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.yimoorua.common.utils.DeptHelper;
import top.yimoorua.common.utils.MenuHelper;
import top.yimoorua.model.system.SysDept;
import top.yimoorua.model.vo.SysDeptQueryVo;
import top.yimoorua.system.service.SysDeptService;
import top.yimoorua.system.mapper.SysDeptMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author haoyang
* @description 针对表【sys_dept(组织机构)】的数据库操作Service实现
* @createDate 2023-04-13 22:23:55
*/
@Transactional
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept>
    implements SysDeptService{

    @Override
    public void updateStatus(String id, Integer status) {
        SysDept sysDept=baseMapper.selectById(id);
        sysDept.setStatus(status);
        baseMapper.updateById(sysDept);
    }

    @Override
    public List<SysDept> findNodes() {
        //获取所有的部门
        List<SysDept> sysDeptList=baseMapper.selectList(null);
        //把所有部门返回要求格式
        List<SysDept> resultList= DeptHelper.buildTree(sysDeptList);
        return resultList;
    }
}




