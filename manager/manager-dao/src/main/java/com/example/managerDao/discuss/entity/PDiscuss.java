package com.example.managerDao.discuss.entity;

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
public class PDiscuss implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 发布人id
     */
    private Integer userId;

    /**
     * 问题图片
     */
    private String picture;

    /**
     * 描述
     */
    private String describe;

    /**
     * 发布时间
     */
    private LocalDateTime createTime;

    /**
     * 状态 0）删除 1）发布成功 2）被投诉待审核3）成功
     */
    private Integer state;

    /**
     * 问题标题
     */
    private String title;


}
