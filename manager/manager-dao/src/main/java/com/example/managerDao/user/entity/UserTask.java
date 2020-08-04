package com.example.managerDao.user.entity;

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
 * @since 2020-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_task")
public class UserTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

    /**
     * 任务id
     */
    @TableField("bindTaskId")
    private Integer bindTaskId;

    /**
     * 用户userid
     */
    @TableField("bindUserId")
    private String bindUserId;

    /**
     * 用户任务id   主键
     */
    @TableId(value = "userTaskId", type = IdType.AUTO)
    private Long userTaskId;

    /**
     * 任务类型  1：单次；2：周期；
     */
    @TableField("taskType")
    private Integer taskType;

    /**
     * 领取任务时间   时间戳
     */
    @TableField("addTime")
    private Long addTime;

    /**
     * 任务结束时间   时间戳
     */
    @TableField("endTime")
    private Long endTime;

    /**
     * 任务目标
     */
    @TableField("taskTarget")
    private Integer taskTarget;

    /**
     * 已完成目标
     */
    @TableField("alreadyTarget")
    private Integer alreadyTarget;

    /**
     * 还剩余目标
     */
    @TableField("stillNeedTarget")
    private Integer stillNeedTarget;

    /**
     * 任务状态
1：未完成；
2：已完成未领取；
3：已完成已领取；
4：已过期；
     */
    @TableField("taskState")
    private Integer taskState;


}
