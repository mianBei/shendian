package com.example.managerDao.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("platform_message_post")
public class PlatformMessagePost implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

    /**
     * 平台发布消息id   主键
     */
    @TableId(value = "postMessageId", type = IdType.AUTO)
    private Integer postMessageId;

    /**
     * 消息标题
     */
    @TableField("messageTitle")
    private String messageTitle;

    /**
     * 消息简介
     */
    @TableField("messageAbout")
    private String messageAbout;

    /**
     * 消息封面图
     */
    @TableField("messageCoverImage")
    private String messageCoverImage;

    /**
     * 消息发布时间   时间戳
     */
    @TableField("messageTime")
    private Long messageTime;

    /**
     * 消息发布对象
1：全平台；//删除  如果发布全平台的消息就发布全用户和全商家
2：全用户；
3：全商家；
4：部分用户；
5：部分商家；
6：新用户；
7：新商家；
8：全代理；
9：部分代理；
10：新代理；
     */
    @TableField("messageObject")
    private Integer messageObject;

    /**
     * 发布商家数组对象
[
    {
        shopId：商家id,
        isRead：是否已读，0：未读；1：已读
        isDel：是否删除，0：已删除；1：未删除
    }
]
     */
    @TableField("postShopArr")
    private String postShopArr;

    /**
     * 发布用户数组对象
[
   {
       userId：用户userid，
       isRead：是否已读，0：未读；1：已读；
       isDel：是否删除，0：已删除；1：未删除；
   }
]
     */
    @TableField("postUserArr")
    private String postUserArr;

    /**
     * 发布代理数组对象
[
   {
       agentId：代理id，
       isRead：是否已读，0：未读；1：已读；
       isDel：是否删除，0：已删除；1：未删除；
   }
]
     */
    @TableField("postAgentArr")
    private String postAgentArr;

    /**
     * 消息数据  //富文本html数据
     */
    @TableField("messageData")
    private String messageData;

    /**
     * 发布消息人员平台userid
     */
    @TableField("postPlatformUserId")
    private String postPlatformUserId;

    /**
     * 是否删除   0：已删除；1：未删除；
     */
    @TableField("isDel")
    private Integer isDel;


}
