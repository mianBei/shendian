package com.example.managerDao.archives.entity;

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
@TableName("p_archives_feedback")
public class PArchivesFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关联档案
     */
    private Integer archivesNumber;

    /**
     * 内容
     */
    private String content;

    /**
     * 反馈用户
     */
    private Integer userId;

    private LocalDateTime createTime;

    /**
     * 图片
     */
    private String picture;

    /**
     * 0)未审核 1）已审核
     */
    private Integer state;

    /**
     * 回复内容
     */
    private String replyCon;

    /**
     * 回复时间
     */
    private LocalDateTime replyTime;

    /**
     * 反馈类型  比如填写错误，等等
     */
    private Integer type;


}
