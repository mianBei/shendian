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
@TableName("shop_help_gratis_event")
public class ShopHelpGratisEvent implements Serializable {

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
     * 助力免单活动id   主键
     */
    @TableId(value = "helpGratisEventId", type = IdType.AUTO)
    private Long helpGratisEventId;

    /**
     * 助力免单活动商品数组对象
     */
    @TableField("commodityArr")
    private String commodityArr;

    /**
     * 助力免单活动名称
     */
    @TableField("helpGratisEventName")
    private String helpGratisEventName;

    /**
     * 需助力人数
     */
    @TableField("needHelpNum")
    private Integer needHelpNum;

    /**
     * 商品价值金额（原价）
     */
    @TableField("commodityWorth")
    private BigDecimal commodityWorth;

    /**
     * 免单价
     */
    @TableField("gratisAMT")
    private BigDecimal gratisAMT;

    /**
     * 助力免单活动有效期  单位：天；
     */
    @TableField("helpGratisEventIndate")
    private Integer helpGratisEventIndate;

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
     * 活动发布时间   时间戳
     */
    @TableField("addTime")
    private Long addTime;

    /**
     * 已成功数量（销量）
     */
    @TableField("salesNum")
    private Integer salesNum;

    /**
     * 活动状态
1：未开始；
2：已开始
3：已过期；
     */
    @TableField("eventState")
    private String eventState;

    /**
     * 活动数字状态
1：未开始；
2：已开始；
3：已过期；
     */
    @TableField("eventStateNum")
    private Integer eventStateNum;
    /**
     * 商家ID
     */
    @TableField("commodityId")
    private int commodityId;
}
