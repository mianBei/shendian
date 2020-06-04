package com.example.managerDao.user.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class PUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    /**
     * 1)男 2）女
     */
    private Integer sex;

    /**
     * 头像地址
     */
    private String headPortrait;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 关联宠物表  pet
     */
    private Integer petId;

    /**
     * 微信的openID
     */
    private String openid;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 1)正常 0）封号
     */
    private Integer sealState;

    /**
     * 当前用户信用总积分
     */
    private Integer reputation;


}
