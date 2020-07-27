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
 * @since 2020-07-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("platform_basic_info")
public class PlatformBasicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableId(value = "platformId", type = IdType.AUTO)
    private Integer platformId;

    /**
     * 平台名称
     */
    @TableField("platformName")
    private String platformName;

    /**
     * 平台Logo
     */
    @TableField("platformLogo")
    private String platformLogo;

    /**
     * 平台联系电话
     */
    @TableField("platformTel")
    private String platformTel;

    /**
     * 平台收益百分比  相对于商家让利比例的百分比不是相对于用户实际付款的百分比
     */
    @TableField("platformEarningsPercent")
    private Integer platformEarningsPercent;

    /**
     * 腾讯云SecretId  访问密钥
     */
    @TableField("secretId")
    private String secretId;

    /**
     * 腾讯云SecretKey  访问密钥
     */
    @TableField("secretKey")
    private String secretKey;

    /**
     * 验证码位数
     */
    @TableField("verificationCode")
    private Integer verificationCode;

    /**
     * 验证码有效期  单位秒
     */
    @TableField("verificationCodeTime")
    private Integer verificationCodeTime;

    /**
     * 平台规定商户提现百分比  
     */
    @TableField("shopWithdrawBatio")
    private BigDecimal shopWithdrawBatio;

    /**
     * 微信小程序手续费百分比
     */
    @TableField("wxAppWithdrawBatio")
    private BigDecimal wxAppWithdrawBatio;

    /**
     * 平台规定代理提现百分比  
     */
    @TableField("agentWithdrawBatio")
    private BigDecimal agentWithdrawBatio;


}
