package com.example.managerDao.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PlatformRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ruleId", type = IdType.AUTO)
    private Integer ruleId;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限路径
     */
    private String url;

    /**
     * 父菜单ID 0 为一级菜单
     */
    @TableField("parentId")
    private Integer parentId;

    /**
     * 是否为菜单:0 否 1是
     */
    @TableField("isMenu")
    private Integer isMenu;

    /**
     * 排序
     */
    @TableField("orderValue")
    private Integer orderValue;

    /**
     * 菜单图标样式
     */
    @TableField("className")
    private String className;

    /**
     * 功能名称
     */
    @TableField("functionName")
    private String functionName;

    @TableField("createTime")
    private LocalDateTime createTime;

    @TableField("updateTime")
    private LocalDateTime updateTime;

    /**
     * 参数
     */
    private String parameter;


}
