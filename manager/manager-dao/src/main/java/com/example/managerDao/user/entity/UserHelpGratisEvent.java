package com.example.managerDao.user.entity;

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
@TableName("user_help_gratis_event")
public class UserHelpGratisEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

    /**
     * 用户userid
     */
    @TableField("bindUserId")
    private String bindUserId;

    /**
     * 助力免单活动id
     */
    @TableField("bindHelpGratisEventId")
    private Long bindHelpGratisEventId;

    /**
     * 商家id
     */
    @TableField("bindShopId")
    private Integer bindShopId;

    /**
     * 用户助力免单活动id   主键
     */
    @TableId(value = "userHelpGratisEventId", type = IdType.AUTO)
    private Long userHelpGratisEventId;

    /**
     * 参与时间   时间戳
     */
    @TableField("addTime")
    private Long addTime;

    /**
     * 活动结束时间   时间戳
     */
    @TableField("endTime")
    private Long endTime;

    /**
     * 需助力人数
     */
    @TableField("needHelpNum")
    private Integer needHelpNum;

    /**
     * 已经助力人数
     */
    @TableField("alreadyHelpNum")
    private Integer alreadyHelpNum;

    /**
     * 仍需助力人数
     */
    @TableField("stillNeedHelpNum")
    private Integer stillNeedHelpNum;

    /**
     * 已助力人员信息数组对象
[
   {
       userId：
       helpTime：//助力时间   时间戳
   },
   .......
]
     */
    @TableField("helpUserArr")
    private String helpUserArr;

    /**
     * 助力免单活动商品数据数组对象
     */
    @TableField("helpGratisEventCommodityArr")
    private String helpGratisEventCommodityArr;

    /**
     * 用户助力免单活动状态
1：未成功；
2：成功但未下单；
3：成功已下单；
4：已过期；
     */
    @TableField("userEventState")
    private Integer userEventState;

    /**
     * 分享二维码
     */
    @TableField("shareQRCode")
    private String shareQRCode;


}
