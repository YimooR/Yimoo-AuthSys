package top.yimoorua.model.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class BaseEntity implements Serializable {
    /**
     *   ASSIGN_ID雪花算法,19位数字; ASSIGN_UUID uuid;Auto 自动增长;INPUT 设置主键值
     *     @TableId(type = IdType.AUTO)
     *     private Long id;
     *     解决javascript范围过大问题
     */
    private String id;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableLogic  //逻辑删除 默认效果 0 没有删除 1 已经删除
    @TableField("is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private Map<String,Object> param = new HashMap<>();
}
