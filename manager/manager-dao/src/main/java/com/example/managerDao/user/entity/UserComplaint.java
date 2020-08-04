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
 * @since 2020-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_complaint")
public class UserComplaint implements Serializable {

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
     * 用户userid
     */
    @TableField("bindUserId")
    private String bindUserId;

    /**
     * 投诉id   主键
     */
    @TableId(value = "complainId", type = IdType.AUTO)
    private Long complainId;

    /**
     * 投诉类型
     */
    @TableField("complainType")
    private String complainType;

    /**
     * 投诉内容
     */
    @TableField("complainContent")
    private String complainContent;

    /**
     * 联系人姓名
     */
    @TableField("linkName")
    private String linkName;

    /**
     * 联系人电话
     */
    @TableField("linkTel")
    private String linkTel;

    /**
     * 投诉时间  时间戳
     */
    @TableField("complainTime")
    private Long complainTime;

    /**
     * 是否上传图片   0：未上传；1：已上传
     */
    @TableField("isImage")
    private Integer isImage;

    /**
     * 上传图片数组
     */
    @TableField("imageArr")
    private String imageArr;

    /**
     * 是否已处理   0：未处理；1：已处理
     */
    @TableField("isDispose")
    private Integer isDispose;

    /**
     * 是否删除（撤销）0：已删除；1：未删除；
     */
    @TableField("isDel")
    private Integer isDel;

    /**
     * 处理结果
     */
    @TableField("disposeResult")
    private String disposeResult;

    /**
     * 处理人员  平台uerid
     */
    @TableField("bindPlatformUserId")
    private String bindPlatformUserId;

    /**
     * 用户撤销投诉原因
     */
    @TableField("delCause")
    private String delCause;

    /**
     * 撤销类型   1：平台撤销；2：用户撤销
     */
    @TableField("delType")
    private Integer delType;


}
