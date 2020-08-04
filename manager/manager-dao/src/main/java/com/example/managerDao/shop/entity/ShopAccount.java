package com.example.managerDao.shop.entity;

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
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shop_account")
public class ShopAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

    /**
     * 店铺id
     */
    @TableField("shopId")
    private Integer shopId;

    /**
     * 店铺账户id   主键
     */
    @TableId(value = "shopAccountId", type = IdType.AUTO)
    private Long shopAccountId;

    /**
     * 店铺账户账号
     */
    @TableField("shopAccountCode")
    private String shopAccountCode;

    /**
     * 店铺账户密码
     */
    @TableField("shopAccountPassword")
    private String shopAccountPassword;

    /**
     * 店铺账户身份   1最大
     */
    @TableField("shopAccountAuth")
    private Integer shopAccountAuth;

    /**
     * 店铺绑定用户userid
     */
    @TableField("bindUserId")
    private String bindUserId;

    /**
     * 成为该店铺账户时间
     */
    @TableField("addTime")
    private Long addTime;

    /**
     * 是否删除   0：删除；1：未删除
     */
    @TableField("isDel")
    private Integer isDel;

    /**
     * 操作人绑定用户端userid
     */
    @TableField("operatorBindUserId")
    private String operatorBindUserId;

    /**
     * 关联role
     */
    @TableField("roleId")
    private int roleId;


}
