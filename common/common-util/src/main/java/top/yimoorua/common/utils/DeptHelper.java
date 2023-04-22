package top.yimoorua.common.utils;

import top.yimoorua.model.system.SysDept;
import top.yimoorua.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DeptHelper
 * @Description TODO
 * @date 2023/4/19 23:23
 * @Version 1.0
 * @Author hao yang
 */
public class DeptHelper {
    /**
     * 构建树形结构
     * @param sysDeptList
     * @return
     */
    public static List<SysDept> buildTree(List<SysDept> sysDeptList) {
        //创建集合封装最终的数据
        List<SysDept> trees=new ArrayList<>();
        //遍历所有菜单的集合
        for(SysDept sysDept:sysDeptList){
            //找到递归的入口，parentId=0
            if(sysDept.getParentId().longValue()==0){
                trees.add(findChildren(sysDept,sysDeptList));
            }
        }
        return trees;
    }

    /**
     * 从根节点进行递归查询，查询子接口
     * 判断id==parentId,true为子节点进行封装
     * @param sysDept
     * @param sysDeptList
     * @return
     */
    private static SysDept findChildren(SysDept sysDept, List<SysDept> sysDeptList) {
        //数据初始化
        sysDept.setChildren(new ArrayList<SysDept>());

        //遍历递归查找
        for(SysDept it:sysDeptList){
            //获取当前菜单id
            String id=sysDept.getId();
            //获取所有菜单的parentId
            //比对
            if(Long.parseLong(id)==it.getParentId()) {
                if(sysDept.getChildren()==null){
                    sysDept.setChildren(new ArrayList<>());
                }
                sysDept.getChildren().add(findChildren(it, sysDeptList));
            }
        }
        return sysDept;
    }
}
