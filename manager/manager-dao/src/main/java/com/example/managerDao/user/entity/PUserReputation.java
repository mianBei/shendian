package com.example.managerDao.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("p_user_reputation")
public class PUserReputation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关联投诉表
     */
    private Integer complaintId;

    /**
     * 描述下扣除或增长信息
     */
    private String describe;

    /**
     * 1）增加 2）扣除
     */
    private Integer state;

    /**
     * 信用分数
     */
    private Integer fraction;

    private LocalDateTime createTime;

    /**
     * 扣除用户
     */
    private Integer userId;

    /**
     * 关联字段表 普通用户  医生
     */
    private Integer userType;

    /**
     * 平台执行人
     */
    private Integer platUserId;


}
