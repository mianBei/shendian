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
@TableName("p_discuss_fabulous")
public class PDiscussFabulous implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关联用户id
     */
    private Integer userId;

    /**
     * 关联讨论表id
     */
    private Integer discussId;

    /**
     * 状态 1）点赞 2）取消点赞
     */
    private Integer state;

    /**
     * 用户类型  1）医生2）普通用户
     */
    private Integer type;

    private LocalDateTime createTime;

    /**
     * 关联评论
     */
    private Integer commentId;


}
