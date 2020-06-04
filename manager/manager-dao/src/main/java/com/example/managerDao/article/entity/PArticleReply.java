package com.example.managerDao.article.entity;

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
@TableName("p_article_reply")
public class PArticleReply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关联评论id
     */
    private Integer articleCommentId;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 回复用户id
     */
    private Integer userId;

    /**
     * 1）回复 2）取消回复
     */
    private Integer state;

    /**
     * 被回复用户
     */
    private Integer repliedUserId;


}
