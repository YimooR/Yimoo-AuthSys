package top.yimoorua.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.yimoorua.model.system.SysPost;
import top.yimoorua.system.service.SysPostService;
import top.yimoorua.system.mapper.SysPostMapper;
import org.springframework.stereotype.Service;

/**
* @author haoyang
* @description 针对表【sys_post(岗位信息表)】的数据库操作Service实现
* @createDate 2023-04-18 22:49:54
*/
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost>
    implements SysPostService{

    @Override
    public void updateStatus(String id, Integer status) {
        SysPost sysPost=baseMapper.selectById(id);
        sysPost.setStatus(status);
        baseMapper.updateById(sysPost);
    }
}




