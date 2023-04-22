package top.yimoorua.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yimoorua.model.system.SysDept;
import top.yimoorua.model.vo.SysDeptQueryVo;

import java.util.List;

/**
* @author haoyang
* @description 针对表【sys_dept(组织机构)】的数据库操作Service
* @createDate 2023-04-13 22:23:55
*/
public interface SysDeptService extends IService<SysDept> {

    void updateStatus(String id, Integer status);

    List<SysDept> findNodes();
}
