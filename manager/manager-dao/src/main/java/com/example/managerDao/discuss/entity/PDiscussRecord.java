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
@TableName("p_discuss_record")
public class PDiscussRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 对应讨论文章 discuss
     */
    private Integer discussId;

    /**
     * 谁看过该文章
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
