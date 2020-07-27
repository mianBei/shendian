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
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PlatformOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

    /**
     * 该订单userid
     */
    @TableField("bindUserId")
    private String bindUserId;

    /**
     * 下单商家id
     */
    @TableField("bindShopId")
    private Integer bindShopId;

    /**
     * 订单绑定openId
     */
    @TableField("bindOpenId")
    private String bindOpenId;

    /**
     * 订单id
     */
    @TableId(value = "orderId", type = IdType.AUTO)
    private Long orderId;

    /**
     * 订单付款类型
1：线下；
2：线上：2.1：抢购活动；2.2：助力免单；
     */
    @TableField("orderPaymentType")
    private Integer orderPaymentType;

    /**
     * 订单付款状态
1：待付款；
2：排队中；
3：已免单；
4：已完成；

     */
    @TableField("orderState")
    private String orderState;

    /**
     * 订单付款数字状态
1：待付款；
2：排队中；
3：已免单；
4：已完成；

     */
    @TableField("orderStateNum")
    private Integer orderStateNum;

    /**
     * 是否已取货    0：未取货；1：已取货；  
订单付款类型为线上时有该字段
     */
    @TableField("isGetCargo")
    private Integer isGetCargo;

    /**
     * 订单号
     */
    @TableField("orderCode")
    private String orderCode;

    /**
     * 微信订单号
     */
    @TableField("wxOrderCode")
    private String wxOrderCode;

    /**
     * 下单时间   时间戳
     */
    @TableField("orderTime")
    private Long orderTime;

    /**
     * 付款结束时间   时间戳
     */
    @TableField("paymentEndTime")
    private Long paymentEndTime;

    /**
     * 付款时间  时间戳
     */
    @TableField("paymentTime")
    private Long paymentTime;

    /**
     * 订单更新时间   时间戳
     */
    @TableField("updateTime")
    private Long updateTime;

    /**
     * 抢购活动id   订单付款类型为抢购活动时  有该字段
     */
    @TableField("snapUpEventId")
    private Long snapUpEventId;

    /**
     * 抢购活动商品数组对象   订单付款类型为抢购活动时  有该字段
     */
    @TableField("snapUpEventCommodityArr")
    private String snapUpEventCommodityArr;

    /**
     * 抢购活动图片
     */
    @TableField("snapUpEventImage")
    private String snapUpEventImage;

    /**
     * 助力免单活动id   订单付款类型为助力免单活动时  有该字段
     */
    @TableField("helpGratisEventId")
    private Long helpGratisEventId;

    /**
     * 助力免单活动商品数组对象    订单付款类型为助力免单活动时  有该字段
     */
    @TableField("helpGratisEventCommodityArr")
    private String helpGratisEventCommodityArr;

    /**
     * 助力免单图片
     */
    @TableField("helpGratisEventImage")
    private String helpGratisEventImage;

    /**
     * 用户助力免单活动id
     */
    @TableField("userHelpGratisEventId")
    private Long userHelpGratisEventId;

    /**
     * 订单核销二维码   线上订单才有
     */
    @TableField("orderQRCode")
    private String orderQRCode;

    /**
     * 订单金额
     */
    @TableField("orderAMT")
    private BigDecimal orderAMT;

    /**
     * 订单优惠金额
     */
    @TableField("discountsAMT")
    private BigDecimal discountsAMT;

    /**
     * 实付金额
     */
    @TableField("realityAMT")
    private BigDecimal realityAMT;

    /**
     * 用户是否使用优惠券   0：没有；1：用了；
     */
    @TableField("isCoupon")
    private Integer isCoupon;

    /**
     * 用户优惠券id   当用户使用优惠券时才有
     */
    @TableField("userCouponId")
    private Long userCouponId;

    /**
     * 优惠券抵扣金额
     */
    @TableField("couponAMT")
    private BigDecimal couponAMT;

    /**
     * 用户是否使用md豆   0：没有；1：使用了；
     */
    @TableField("isMDD")
    private Integer isMDD;

    /**
     * 用户使用md豆数量
     */
    @TableField("useMDDNum")
    private BigDecimal useMDDNum;

    /**
     * md豆抵扣金额
     */
    @TableField("MDDAMT")
    private BigDecimal mddamt;

    /**
     * 用户是否使用md币    0：没有；1：使用了；
     */
    @TableField("isMDB")
    private Integer isMDB;

    /**
     * 用户使用md币数量
     */
    @TableField("useMDBNum")
    private BigDecimal useMDBNum;

    /**
     * md币抵扣金额
     */
    @TableField("MDBAMT")
    private BigDecimal mdbamt;

    /**
     * 付款方式   如：微信支付，支付宝支付......
     */
    @TableField("paymentMode")
    private String paymentMode;

    /**
     * 是否参与排队退返   0：没参与；1：参与了；
     */
    @TableField("isQueue")
    private Integer isQueue;

    /**
     * 排队退返类型   1：排队退返；2：快速退返；
     */
    @TableField("QueueType")
    private Integer QueueType;

    /**
     * 进入排队时间   时间戳
     */
    @TableField("inQueueTime")
    private Long inQueueTime;

    /**
     * 排队退返完成时间   时间戳
     */
    @TableField("QueueSucceedTime")
    private Long QueueSucceedTime;

    /**
     * 用户使用快返券时间（快速退返时间）  时间戳
     */
    @TableField("quickRefundsTime")
    private Long quickRefundsTime;

    /**
     * 是否有分销人   0：没有；1：有；
     */
    @TableField("isDistribution")
    private Integer isDistribution;

    /**
     * 分销人userid
     */
    @TableField("distributionBindUserId")
    private String distributionBindUserId;

    /**
     * 是否正在收集排队退返金额   0：没有；1：正在收集；
     */
    @TableField("isJustCollect")
    private Integer isJustCollect;

    /**
     * 已收集排队退返金额
     */
    @TableField("collectAMT")
    private BigDecimal collectAMT;

    /**
     * 还差多少金额完成排队退返
     */
    @TableField("shortCollectAMT")
    private BigDecimal shortCollectAMT;

    /**
     * 订单排队退返名次  从1开始  第一的开始收集免单金额
     */
    @TableField("queueRank")
    private Integer queueRank;

    /**
     * 排队成功名次  第几位免单用户
     */
    @TableField("queueSucceedRank")
    private Integer queueSucceedRank;

    /**
     * 用户是否删除   0：已删除；1：未删除；
     */
    @TableField("isDel")
    private Integer isDel;

    /**
     * 备注
     */
    private String remark;

    /**
     * 该订单是否绑定了userId  0：没有绑定；1：绑定了
     */
    @TableField("isBindUser")
    private Integer isBindUser;


}
