package com.example.managerDao.order.entity;

import java.math.BigDecimal;
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
 * @since 2020-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PlatformBill implements Serializable {

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

收入：订单收入、商家购买商品展位、商家购买抢购活动、用户购买快返券。。。

支出：。。。
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
     * 付款方式
     */
    @TableField("paymentMode")
    private String paymentMode;

    /**
     * 付款类型    商家支付、用户支付、订单分成
     */
    @TableField("paymentType")
    private String paymentType;

    /**
     * 绑定订单数组对象
[
   {
      orderId：//订单id
   }
]
     */
    @TableField("bindOrderArr")
    private String bindOrderArr;


}
