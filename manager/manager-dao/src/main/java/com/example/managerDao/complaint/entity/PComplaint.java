package com.example.managerDao.complaint.entity;

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
public class PComplaint implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 投诉人ID
     */
    private Integer plaintiffUserId;

    /**
     * 投诉类别 关联字典表
     */
    private Integer type;

    /**
     * 用户类型 关联字典表 type
     */
    private Integer userType;

    /**
     * 描述
     */
    private String describe;

    /**
     * 图片
     */
    private String picture;

    /**
     * 来源 1）文章 2）医生交流 3）群组 4）贴吧
     */
    private Integer postion;

    private LocalDateTime createTime;

    /**
     * 状态 0）未审核 1）通过 2）拒绝
     */
    private Integer state;

    /**
     * 被投诉位置文章 位置是社区显示 社区的id 群组显示群组id
     */
    private Integer defendantId;

    /**
     * 反馈投诉人内容
     */
    private String replyCon;

    private LocalDateTime replyTime;

    /**
     * 被投诉人id
     */
    private Integer respondentUserId;

    /**
     * 反馈被投诉人内容
     */
    private String respondentCon;


}
