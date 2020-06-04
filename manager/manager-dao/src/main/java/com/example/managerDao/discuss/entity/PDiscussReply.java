package com.example.managerDao.discuss.entity;

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
@TableName("p_discuss_reply")
public class PDiscussReply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 回复用户
     */
    private Integer replyUser;

    /**
     * 被回复用户
     */
    private Integer noReplyUser;

    private Integer commentId;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态 1）回复成功 2）取消回复
     */
    private Integer state;

    /**
     * 用户类型  1）医生  2）普通用户
     */
    private String type;

    private LocalDateTime createTime;


}
