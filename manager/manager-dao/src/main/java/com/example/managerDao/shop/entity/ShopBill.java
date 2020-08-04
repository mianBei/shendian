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
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shop_bill")
public class ShopBill implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

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

收入：提现。。。

支出：购买商品展位、抢购活动发布。。。
     */
    @TableField("billType")
    private String billType;

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
     * 付款方式  打款到零钱  微信支付
     */
    @TableField("paymentMode")
    private String paymentMode;

    /**
     * 付款类型    平台支付、企业打款
     */
    @TableField("paymentType")
    private String paymentType;


}
