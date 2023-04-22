package top.yimoorua.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName SysDeptQueryVo
 * @Description 部门查询实体
 * @date 2023/4/13 22:50
 * @Version 1.0
 * @Author hao yang
 */
@Data
public class SysDeptQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String deptName;

}
