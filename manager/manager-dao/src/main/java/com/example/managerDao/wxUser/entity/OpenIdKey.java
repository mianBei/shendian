package com.example.managerDao.wxUser.entity;

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
 * @since 2020-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OpenIdKey implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * openIdSessionKeyid主键
     */
    @TableId(value = "openIdKeyId", type = IdType.AUTO)
    private Integer openIdKeyId;

    @TableField("openId")
    private String openId;

    @TableField("sessionKey")
    private String sessionKey;

    /**
     * 类型：1：app；2：微信小程序
     */
    @TableField("appType")
    private Integer appType;

    /**
     * 用户上一次登录的userid
     */
    @TableField("userId")
    private String userId;


}
