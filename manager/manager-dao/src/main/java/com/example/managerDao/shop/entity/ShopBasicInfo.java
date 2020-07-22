package com.example.managerDao.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("shop_basic_info")
public class ShopBasicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

    /**
     * 绑定userId  店主userId
     */
    @TableField("bindUserId")
    private String bindUserId;

    /**
     * 店铺id
     */
    @TableId("shopId")
    private Integer shopId;

    /**
     * 店铺名称
     */
    @TableField("shopName")
    private String shopName;

    /**
     * 店铺logo
     */
    @TableField("shopLogo")
    private String shopLogo;

    /**
     * 店铺问候欢迎语
     */
    @TableField("shopGreeting")
    private String shopGreeting;

    /**
     * 店铺简介
     */
    @TableField("shopInfo")
    private String shopInfo;

    /**
     * 店铺所属省
     */
    @TableField("shopBelongProvince")
    private String shopBelongProvince;

    /**
     * 店铺所属市
     */
    @TableField("shopBelongCity")
    private String shopBelongCity;

    /**
     * 店铺所属区
     */
    @TableField("shopBelongArea")
    private String shopBelongArea;

    /**
     * 店铺详细地址
     */
    @TableField("shopDetailAddress")
    private String shopDetailAddress;

    /**
     * 店铺所处地址经度
     */
    @TableField("shopLong")
    private String shopLong;

    /**
     * 店铺所处地址纬度
     */
    @TableField("shopLat")
    private String shopLat;

    /**
     * 店铺营业时间
     */
    @TableField("shopBusinessTime")
    private String shopBusinessTime;

    /**
     * 店铺所属行业
     */
    @TableField("shopBelongTrade")
    private String shopBelongTrade;

    /**
     * 店铺所属行业id
     */
    @TableField("tradeId")
    private Integer tradeId;

    /**
     * 店铺入驻时间  时间戳
     */
    @TableField("shopEnterTime")
    private Long shopEnterTime;

    /**
     * 店铺是否通过申请后是否配置店铺  0：没有；1：配置了
     */
    @TableField("isConfig")
    private Integer isConfig;

    /**
     * 店铺配置时间  时间戳
     */
    @TableField("configTime")
    private Long configTime;

    /**
     * 店铺轮播图数组  json字符串
     */
    @TableField("shopBanner")
    private String shopBanner;

    /**
     * 是否开启通兑服务（md豆抵扣现金）   0：关闭；1开启
     */
    private Integer ismdd;

    /**
     * 是否有代理商  0：没有；1有；
     */
    @TableField("isAgent")
    private Integer isAgent;

    /**
     * 代理商id
     */
    @TableField("agentId")
    private Integer agentId;

    /**
     * 店主userid
     */
    @TableField("shopkeeperUserId")
    private String shopkeeperUserId;

    /**
     * 店铺选择折扣
     */
    @TableField("shopSelectDiscounts")
    private BigDecimal shopSelectDiscounts;

    /**
     * 店铺优惠百分比   如：店铺打9折，这里写10
公式：（1-9/10）*100=10
意思是让利10%
店铺设置打折比例允许设置小数点后1位
如：店铺打折9.5
     */
    @TableField("shopDiscountsPercentum")
    private Integer shopDiscountsPercentum;

    /**
     * 是否显示   0：不显示；1：显示
     */
    @TableField("isShow")
    private Integer isShow;

    /**
     * 店铺星级
     */
    @TableField("shopStar")
    private Integer shopStar;

    /**
     * 店铺累计入款笔数
     */
    @TableField("cashAccountTotalNum")
    private Integer cashAccountTotalNum;

    /**
     * 是否发布优惠券   0：没有；1：有
     */
    @TableField("isCoupon")
    private Integer isCoupon;

    /**
     * 是否有瓜分券  0：没有；1：有
     */
    @TableField("isDivideUpCoupon")
    private Integer isDivideUpCoupon;

    /**
     * 是否有助力免单活动   0：没有；1：有
     */
    @TableField("isHelpGratisEvent")
    private Integer isHelpGratisEvent;

    /**
     * 是否有抢购活动  0：没有；1：有
     */
    @TableField("isSnapUpEvent")
    private Integer isSnapUpEvent;

    /**
     * 是否是平台推荐   0：不是；1：是
     */
    @TableField("isRecommend")
    private Integer isRecommend;

    /**
     * 是否有展示商品   0：没有；1：有
     */
    @TableField("isCommodity")
    private Integer isCommodity;

    /**
     * 已排队退款笔数
     */
    @TableField("queueRefundNum")
    private Integer queueRefundNum;

    /**
     * 付款二维码
     */
    @TableField("paymentQRCode")
    private String paymentQRCode;

    /**
     * 被投诉数量
     */
    @TableField("complainNum")
    private Integer complainNum;


}
