package com.example.managerDao.article.entity;

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
public class PArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 作者 关联医生用户，只有医生可以写文章
     */
    private String doctorId;

    /**
     * 内容
     */
    private String content;

    /**
     * 文章类别 内科文章 外科文章
     */
    private String type;

    /**
     * 1）医生添加 2）平台添加
     */
    private Integer state;


}
