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
 * @since 2020-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("platform_user_role")
public class PlatformUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户  角色关联表
     */
    @TableId(value = "userRoleId", type = IdType.AUTO)
    private Integer userRoleId;

    /**
     * 用户
     */
    @TableField("platUserId")
    private Integer platUserId;

    /**
     * 角色
     */
    @TableField("roleId")
    private Integer roleId;


}
