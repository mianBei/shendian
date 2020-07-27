package com.example.managerDao.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("platform_task")
public class PlatformTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

    /**
     * 任务id   主键
     */
    @TableId(value = "taskId", type = IdType.AUTO)
    private Integer taskId;

    /**
     * 任务类型  1：单次；2：周期；
     */
    @TableField("taskType")
    private Integer taskType;

    /**
     * 任务名称
     */
    @TableField("taskName")
    private String taskName;

    /**
     * 任务图标
     */
    @TableField("taskIcon")
    private String taskIcon;

    /**
     * 任务完成奖励数组对象
[
    {
        awardType：//奖励类型   md豆、快返券、优惠券...
        awardNum：//奖励数量
        bindCouponId：绑定优惠券id
    },
    .......
]
     */
    @TableField("taskAwardArr")
    private String taskAwardArr;

    /**
     * 任务有效期   单位天数   周期才有
     */
    @TableField("taskIndate")
    private Integer taskIndate;

    /**
     * 任务绑定表
     */
    @TableField("taskBindTable")
    private String taskBindTable;

    /**
     * 任务绑定表字段
     */
    @TableField("taskBindTableFields")
    private String taskBindTableFields;

    /**
     * 任务绑定表字段值为什么时任务完成   单次才有
     */
    @TableField("taskBindTableFieldsValue")
    private Integer taskBindTableFieldsValue;

    /**
     * 任务目标值   周期才有
     */
    @TableField("taskTarget")
    private Integer taskTarget;


}
