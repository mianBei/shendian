package com.example.managerDao.platform.entity;

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
 * @since 2020-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("platform_payment_config")
public class PlatformPaymentConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

    /**
     * 平台付款配置id  主键
     */
    @TableId(value = "paymentConfigId", type = IdType.AUTO)
    private Integer paymentConfigId;

    /**
     * 使用者类型  1：用户端和商家端；3：平台端；

微信小程序appid必须与商户号绑定，目前该字段值就为1  因为目前商户号（企业付款，分账等等）的使用就只在用户端、商家端、代理端操作而这些端在一个小程序里
     */
    @TableField("userType")
    private Integer userType;

    /**
     * 程序类型
      1：app；2：微信小程序
     */
    @TableField("appType")
    private Integer appType;

    /**
     * 付款配置数据（json字符串）：
微信小程序
{
   mchId：商户号；
   mchKey：微信商户平台设置key；
   type：分账接受方类型；
   relation_type：与分账方的关系；
   custom_relation：自定已与分账方的关系；
}
注：relation_type为CUSTOM时有custom_relation
     */
    @TableField("paymentConfigData")
    private String paymentConfigData;

    /**
     * 备注
     */
    private String remark;

    /**
     * 每天提现金额  不超过9万
     */
    @TableField("toDayWithdrawAMT")
    private BigDecimal toDayWithdrawAMT;

    /**
     * 是否是主要的商户号  0：不是；1：是   
isReceiver为1时该字段才能为1且只有一个1
     */
    @TableField("isMain")
    private Integer isMain;


}
