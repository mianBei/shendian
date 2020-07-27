package com.example.managerDao.platform.entity;

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
@TableName("platform_message_system")
public class PlatformMessageSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

    /**
     * 消息id   主键
     */
    @TableId(value = "messageId", type = IdType.AUTO)
    private Integer messageId;

    /**
     * 消息标题
     */
    @TableField("messageTitle")
    private String messageTitle;

    /**
     * 消息内容
     */
    @TableField("messageContent")
    private String messageContent;

    /**
     * 消息时间   时间戳
     */
    @TableField("messageTime")
    private Long messageTime;

    /**
     * 消息跳转页面
     */
    @TableField("messageJumpPage")
    private String messageJumpPage;

    /**
     * 是否已读   0：未读；1：已读；
     */
    @TableField("isRead")
    private Integer isRead;

    /**
     * 是否已处理   0：未处理；1：已处理；2通知
     */
    @TableField("isHandle")
    private Integer isHandle;
}
