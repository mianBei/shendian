package com.example.managerDao.user.entity;

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
public class PlatformRoleRules implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "roleRulesId", type = IdType.AUTO)
    private Integer roleRulesId;

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
