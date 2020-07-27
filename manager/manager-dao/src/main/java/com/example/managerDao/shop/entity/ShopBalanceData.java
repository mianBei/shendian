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
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shop_balance_data")
public class ShopBalanceData implements Serializable {

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
     * 店铺余额表  主键
     */
    @TableId(value = "shopBalanceId", type = IdType.AUTO)
    private Integer shopBalanceId;

    /**
     * 该店排队退返订单数组对象
[
   {
       orderId：订单id
   }
]
     */
    @TableField("shopQueueOrder")
    private String shopQueueOrder;

    /**
     * 店铺累计入款笔数
     */
    @TableField("cashAccountTotalNum")
    private Integer cashAccountTotalNum;

    /**
     * 累计入账现金账户金额
     */
    @TableField("cashAccountTotalAMT")
    private BigDecimal cashAccountTotalAMT;

    /**
     * 已提现现金账户金额
     */
    @TableField("cashAccountWithdrawAMT")
    private BigDecimal cashAccountWithdrawAMT;

    /**
     * 现金账户余额（可提现金额）
     */
    @TableField("cashAccountBalanceAMT")
    private BigDecimal cashAccountBalanceAMT;

    /**
     * 已排队退款金额
     */
    @TableField("queueRefundAMT")
    private BigDecimal queueRefundAMT;

    /**
     * 已排队退款笔数
     */
    @TableField("queueRefundNum")
    private Integer queueRefundNum;

    /**
     * 已排队退款订单对象数组
[
   {
       orderId：订单id
   }
]
     */
    @TableField("queueRefundOrder")
    private String queueRefundOrder;

    /**
     * 代理累计收益金额
     */
    @TableField("agentTotalAMT")
    private BigDecimal agentTotalAMT;


}
