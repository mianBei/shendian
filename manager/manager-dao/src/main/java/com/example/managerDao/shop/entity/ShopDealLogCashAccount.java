package com.example.managerDao.shop.entity;

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
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shop_deal_log_cash_account")
public class ShopDealLogCashAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

    /**
     * 绑定用户userid   交易类型为订单收入时才有此字段
     */
    @TableField("bindUserId")
    private String bindUserId;

    /**
     * 商家id
     */
    @TableField("bindShopId")
    private Integer bindShopId;

    /**
     * 图标id
     */
    @TableField("iconId")
    private Integer iconId;

    /**
     * 现金账户交易记录id   主键
     */
    @TableId(value = "cashAccountDealLogId", type = IdType.AUTO)
    private Long cashAccountDealLogId;

    /**
     * 交易记录标题    交易类型
     */
    @TableField("dealLogTitle")
    private String dealLogTitle;

    /**
     * 收入还是支出   1：收入；2：支出；
     */
    @TableField("inOrOut")
    private Integer inOrOut;

    /**
     * 交易类型

收入：1：到店付款收益、2：抢购活动收益、3：助力免单收益

支出：-1：提现。。。
     */
    @TableField("dealType")
    private String dealType;

    /**
     * 交易类型

收入：1：到店付款收益、2：抢购活动收益、3：助力免单收益

支出：-1：提现。。。
     */
    @TableField("dealTypeNum")
    private Integer dealTypeNum;

    /**
     * 交易金额
     */
    @TableField("dealAMT")
    private BigDecimal dealAMT;

    /**
     * 交易记录时间
     */
    @TableField("addTime")
    private Long addTime;

    /**
     * 提现比例百分比  dealTypeNum为-1时才有此字段
     */
    @TableField("withdrawPercentum")
    private Integer withdrawPercentum;

    /**
     * 提现成多少现金  dealTypeNum为-1时才有此字段
     */
    @TableField("withdrawMoney")
    private BigDecimal withdrawMoney;

    /**
     * 绑定订单id数组  dealTypeNum为1、2、3时才有此字段
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
     * 用户实付金额   dealTypeNum为1、2时才有此字段
     */
    @TableField("userRealityAMT")
    private BigDecimal userRealityAMT;

    /**
     * 店铺让利金额   dealTypeNum为1、2时才有此字段
     */
    @TableField("shopDiscountsAMT")
    private BigDecimal shopDiscountsAMT;

    /**
     * 店铺选择折扣   dealTypeNum为1、2时才有此字段
     */
    @TableField("shopSelectDiscounts")
    private BigDecimal shopSelectDiscounts;

    /**
     * 平台收益金额   dealTypeNum为1、2时才有此字段
     */
    @TableField("platfromAMT")
    private BigDecimal platfromAMT;

    /**
     * 代理收益金额   dealTypeNum为1、2时才有此字段
     */
    @TableField("agentAMT")
    private BigDecimal agentAMT;

    /**
     * 分销佣金金额   dealTypeNum为2时才有此字段
     */
    @TableField("distributionCommissionAMT")
    private BigDecimal distributionCommissionAMT;

    /**
     * 备注
     */
    private String remark;


}
