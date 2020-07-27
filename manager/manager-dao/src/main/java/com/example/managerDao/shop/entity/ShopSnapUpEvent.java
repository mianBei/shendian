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
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shop_snap_up_event")
public class ShopSnapUpEvent implements Serializable {

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
     * 抢购活动id   主键
     */
    @TableId(value = "snapUpEventId", type = IdType.AUTO)
    private Long snapUpEventId;

    /**
     * 抢购活动商品数组对象
     */
    @TableField("commodityArr")
    private String commodityArr;

    /**
     * 抢购数量
     */
    @TableField("snapUpNum")
    private Integer snapUpNum;

    /**
     * 已购买数量（销量）
     */
    @TableField("salesNum")
    private Integer salesNum;

    /**
     * 剩余数量
     */
    @TableField("remainNum")
    private Integer remainNum;

    /**
     * 抢购金额
     */
    @TableField("snapUpAMT")
    private BigDecimal snapUpAMT;

    /**
     * 商品价值金额（原价）
     */
    @TableField("commodityWorth")
    private BigDecimal commodityWorth;

    /**
     * 抢购活动名称
     */
    @TableField("snapUpEventName")
    private String snapUpEventName;

    /**
     * 抢购活动有效期  单位：天
     */
    @TableField("snapUpEventIndate")
    private Integer snapUpEventIndate;

    /**
     * 活动开始时间   时间戳
     */
    @TableField("startTime")
    private Long startTime;

    /**
     * 活动结束时间   时间戳
     */
    @TableField("endTime")
    private Long endTime;

    /**
     * 发布活动时间
     */
    @TableField("addTime")
    private Long addTime;

    /**
     * 活动状态
1：未开始；
2：已开始；
3：已抢完；
4：已过期；
     */
    @TableField("eventState")
    private String eventState;

    /**
     * 活动数字状态
1：未开始；
2：已开始；
3：已抢完；
4：已过期；
     */
    @TableField("eventStateNum")
    private Integer eventStateNum;

    /**
     * 是否开启分销   0：没开启；1：开启了；
     */
    @TableField("isDistribution")
    private Integer isDistribution;

    /**
     * 分销佣金金额
     */
    @TableField("distributionCommissionAMT")
    private BigDecimal distributionCommissionAMT;


}
