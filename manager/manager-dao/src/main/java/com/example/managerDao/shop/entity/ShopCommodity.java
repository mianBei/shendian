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
@TableName("shop_commodity")
public class ShopCommodity implements Serializable {

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
     * 商品id
     */
    @TableId(value = "commodityId", type = IdType.AUTO)
    private Long commodityId;

    /**
     * 商品小图
     */
    @TableField("commoditySubImage")
    private String commoditySubImage;

    /**
     * 商品名称
     */
    @TableField("commodityName")
    private String commodityName;

    /**
     * 商品价格
     */
    @TableField("commodityAMT")
    private BigDecimal commodityAMT;

    /**
     * 商品价值金额（原价）
     */
    @TableField("commodityWorth")
    private BigDecimal commodityWorth;

    /**
     * 商品星级   1-5
     */
    @TableField("commodityStar")
    private Integer commodityStar;

    /**
     * 上传者店铺账户id
     */
    @TableField("uploaderShopAccountId")
    private Long uploaderShopAccountId;

    /**
     * 上传者userid
     */
    @TableField("uploaderUserId")
    private String uploaderUserId;

    /**
     * 上传者身份
     */
    @TableField("uploaderShopAuth")
    private Integer uploaderShopAuth;

    /**
     * 是否删除  0：已删除；1：未删除；
     */
    @TableField("isDel")
    private Integer isDel;

    /**
     * 上传时间
     */
    @TableField("addTime")
    private String addTime;


}
