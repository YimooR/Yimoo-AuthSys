package top.yimoorua.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yimoorua.model.system.SysPost;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author haoyang
* @description 针对表【sys_post(岗位信息表)】的数据库操作Service
* @createDate 2023-04-18 22:49:54
*/
public interface SysPostService extends IService<SysPost> {

    void updateStatus(String id, Integer status);
}
