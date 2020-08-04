package com.example.managerDao.platform.entity;

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
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SmsTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键  短信模板
     */
    @TableId(value = "SMSId", type = IdType.AUTO)
    private Integer SMSId;

    /**
     * 短信类型  1：注册；
     */
    @TableField("SMSType")
    private Integer SMSType;

    /**
     * 腾讯云短信SDKAppID
     */
    @TableField("SMSSDKAppId")
    private String SMSSDKAppId;

    /**
     * 腾讯云短信AppKey
     */
    @TableField("SMSAppKey")
    private String SMSAppKey;

    /**
     * 腾讯云短信模板id
     */
    @TableField("SMSTemplateId")
    private String SMSTemplateId;

    /**
     * 腾讯云短信签名
     */
    @TableField("SMSSign")
    private String SMSSign;

    /**
     * 腾讯云短信正文
     */
    @TableField("SMSContent")
    private String SMSContent;


}
