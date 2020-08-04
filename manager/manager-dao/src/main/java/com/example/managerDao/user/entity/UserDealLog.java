package com.example.managerDao.user.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author jobob
 * @since 2020-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserDealLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("addTime")
    private Long addTime;

    @TableField("bindUserId")
    private String bindUserId;

    @TableField("dealAMT")
    private BigDecimal dealAMT;

    @TableField("dealLogTitle")
    private String dealLogTitle;

    @TableField("dealType")
    private String dealType;

    @TableField("dealTypeNum")
    private Integer dealTypeNum;

    @TableField("iconId")
    private Integer iconId;

    @TableField("inOrOut")
    private Integer inOrOut;

    @TableField("platformId")
    private Integer platformId;
    @TableField("money")
    private BigDecimal money;
    @TableField("percentum")
    private Integer percentum;
    @TableField("type")
    private String type;


}
