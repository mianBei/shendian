package com.example.managerDao.user.entity;

import java.math.BigDecimal;
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
 * @since 2020-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_bill")
public class UserBill implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

    /**
     * 绑定openid
     */
    @TableField("bindOpenId")
    private String bindOpenId;

    /**
     * 绑定店铺id   billTypeNum为-1、-2、-3时才有该字段
     */
    @TableField("bindShopId")
    private Integer bindShopId;

    /**
     * 用户userid
     */
    @TableField("bindUserId")
    private String bindUserId;

    /**
     * 图标id
     */
    @TableField("iconId")
    private Integer iconId;

    /**
     * 账单id   主键
     */
    @TableId(value = "billId", type = IdType.AUTO)
    private Long billId;

    /**
     * 账单标题    账单类型
     */
    @TableField("billTitle")
    private String billTitle;

    /**
     * 收入还是支出   1：收入；2：支出；
     */
    @TableField("inOrOut")
    private Integer inOrOut;

    /**
     * 账单类型

收入：1：提现、2：排队免单。。。

支出：-1：到店买单、-2：抢购活动、-3：助力免单活动、-4：购买快返券。。。
     */
    @TableField("billType")
    private String billType;

    /**
     * 账单类型

收入：1：提现、2：排队免单。。。

支出：-1：到店买单、-2：抢购活动、-3：助力免单活动、-4：购买快返券。。。
     */
    @TableField("billTypeNum")
    private Integer billTypeNum;

    /**
     * 账单金额
     */
    @TableField("billAMT")
    private BigDecimal billAMT;

    /**
     * 交易时间
     */
    @TableField("addTime")
    private Long addTime;

    /**
     * 交易状态
1：交易成功；
2：交易失败；
     */
    @TableField("dealState")
    private Integer dealState;

    /**
     * 账单号
     */
    @TableField("billCode")
    private String billCode;

    /**
     * 绑定订单数组对象
[
   {
      orderId：//订单id
      orderCode：//订单号
   }
]
     */
    @TableField("bindOrderArr")
    private String bindOrderArr;

    /**
     * 付款方式
     */
    @TableField("paymentMode")
    private String paymentMode;

    /**
     * 付款类型    线上支付、线下支付、企业打款
     */
    @TableField("paymentType")
    private String paymentType;

    /**
     * 是否绑定userid  0：没有绑定；1：绑定了
     */
    @TableField("isBindUserId")
    private Integer isBindUserId;

    /**
     * 备注
     */
    private String remark;


}
