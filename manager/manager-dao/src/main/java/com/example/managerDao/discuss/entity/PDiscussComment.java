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
@TableName("p_discuss_comment")
public class PDiscussComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论用户id
     */
    private Integer commentUser;

    /**
     * 被评论用户id
     */
    private Integer noCommentUser;

    /**
     * 讨论问题id
     */
    private Integer discussId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 状态1）成功 0）取消
     */
    private Integer state;

    /**
     * 用户类型  1）医生 2）普通用户
     */
    private Integer type;

    private LocalDateTime createTime;


}
