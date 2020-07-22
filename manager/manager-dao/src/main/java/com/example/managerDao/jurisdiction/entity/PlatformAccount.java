package com.example.managerDao.jurisdiction.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PlatformAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

    /**
     * 平台账户id
     */
    @TableId(value = "platformAccountId", type = IdType.AUTO)
    private Integer platformAccountId;

    /**
     * 平台账户userid
     */
    @TableField("platformAccountUserId")
    private String platformAccountUserId;

    /**
     * 平台账户openid
     */
    @TableField("platformAccountOpenId")
    private String platformAccountOpenId;

    /**
     * 平台账户账号
     */
    @TableField("platformAccountCode")
    private String platformAccountCode;

    /**
     * 平台账户密码
     */
    @TableField("platformAccountPassword")
    private String platformAccountPassword;

    /**
     * 昵称
     */
    @TableField("platformAccountNickName")
    private String platformAccountNickName;

    /**
     * 头像
     */
    @TableField("platformAccountHeadImage")
    private String platformAccountHeadImage;

    /**
     * 绑定手机号
     */
    @TableField("platformAccountPhoneCode")
    private String platformAccountPhoneCode;

    /**
     * 身份  1：最大
     */
    @TableField("platformAccountAuth")
    private Integer platformAccountAuth;

    /**
     * 是否删除  0：已删除；1：未删除；
     */
    @TableField("isDel")
    private Integer isDel;

    /**
     * 备注
     */
    private String remark;
    @TableField("roleId")
    private Integer roleId;


}
