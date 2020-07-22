package com.example.managerDao.jurisdiction.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class PlatformRoleRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "roleRuleId", type = IdType.AUTO)
    private Integer roleRuleId;

    /**
     * 角色ID
     */
    @TableField("roleId")
    private Integer roleId;

    /**
     * 权限ID
     */
    @TableField("ruleId")
    private Integer ruleId;

    /**
     * 父类内容id
     */
    @TableField("parentId")
    private Integer parentId;


}
