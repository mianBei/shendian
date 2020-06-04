package com.example.managerDao.doctor.entity;

import java.time.LocalDateTime;
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
 * @since 2020-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PDoctor implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    /**
     * 1）男2）女
     */
    private Integer sex;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 描述
     */
    private String describe;

    /**
     * 主治，以逗号分隔，关联字典表
     */
    private String indications;

    /**
     * 名字
     */
    private String name;

    /**
     * 身份证
     */
    private String card;

    /**
     * 微信openid
     */
    private String openid;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 关联医院表
     */
    private Integer hospitalId;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 状态：关联字典表 登录状态
     */
    private Integer state;

    /**
     * 1）正常 0）封号
     */
    private Integer sealState;

    /**
     * 当前用户总积分
     */
    private Integer reputation;


}
