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
@TableName("user_balance_data")
public class UserBalanceData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

    /**
     * 用户userid
     */
    @TableField("userId")
    private String userId;

    /**
     * 用户余额id   主键
     */
    @TableId(value = "userBalanceId", type = IdType.AUTO)
    private Integer userBalanceId;

    /**
     * 用户md豆累计获得个数
     */
    @TableField("userMDDTotal")
    private BigDecimal userMDDTotal;

    /**
     * 用户md豆消耗个数
     */
    @TableField("userMDDExpend")
    private BigDecimal userMDDExpend;

    /**
     * 用户md豆余额
     */
    @TableField("userMDDBalance")
    private BigDecimal userMDDBalance;

    /**
     * 用户是否获得md币   0：没有；1：获得了；
     */
    @TableField("userIsMDB")
    private Integer userIsMDB;

    /**
     * 用户md币余额
     */
    @TableField("userMDBBalance")
    private BigDecimal userMDBBalance;

    /**
     * md币绑定商户id   
     */
    @TableField("MDBBindShopId")
    private Integer MDBBindShopId;

    /**
     * 用户是否分享了md币   0：没有；1：分享了；
     */
    @TableField("userIsShareMDB")
    private Integer userIsShareMDB;

    /**
     * 用户分享的md币是否已结束   0：没有；1结束了；
     */
    @TableField("shareMDBIsEnd")
    private Integer shareMDBIsEnd;

    /**
     * 用户佣金总获得
     */
    @TableField("userCommissionTotal")
    private BigDecimal userCommissionTotal;

    /**
     * 用户分销佣金总获得
     */
    @TableField("userDistributionCommissionTotal")
    private BigDecimal userDistributionCommissionTotal;

    /**
     * 用户提现佣金总获得
     */
    @TableField("userWithdrawCommissionTotal")
    private BigDecimal userWithdrawCommissionTotal;

    /**
     * 用户佣金总消耗
     */
    @TableField("userCommissionExpend")
    private BigDecimal userCommissionExpend;

    /**
     * 用户佣金余额
     */
    @TableField("userCommissionBalance")
    private BigDecimal userCommissionBalance;

    /**
     * 用户快返券总获得
     */
    @TableField("quickRefundsTotal")
    private Integer quickRefundsTotal;

    /**
     * 用户快返券总消耗
     */
    @TableField("quickRefundsExpend")
    private Integer quickRefundsExpend;

    /**
     * 用户快返券余额
     */
    @TableField("quickRefundsBalance")
    private Integer quickRefundsBalance;

    /**
     * 用户累计消费现金金额
     */
    @TableField("userCashExpend")
    private BigDecimal userCashExpend;

    /**
     * 用户已付款订单总量
     */
    @TableField("userPaidOrderNum")
    private Integer userPaidOrderNum;

    /**
     * 用户优惠券数量
     */
    @TableField("userCouponNum")
    private Integer userCouponNum;

    /**
     * 用户已排队退款总金额
     */
    @TableField("userQueueRefundAMT")
    private BigDecimal userQueueRefundAMT;

    /**
     * 用户正在排队中的金额
     */
    @TableField("userQueuingAMT")
    private BigDecimal userQueuingAMT;


}
