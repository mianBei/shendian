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
@TableName("p_article_comment")
public class PArticleComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关联文章id
     */
    private Integer articleId;

    /**
     * 关联用户id
     */
    private Integer userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 1）评论成功 2）取消评论
     */
    private Integer state;


}
