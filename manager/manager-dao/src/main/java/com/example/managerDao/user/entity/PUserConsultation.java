package com.example.managerDao.user.entity;

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
@TableName("p_user_consultation")
public class PUserConsultation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 咨询图片
     */
    private String picture;

    /**
     * 描述
     */
    private String describe;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 是否匹配成功 1）成功 0）失败
     */
    private Integer isDoctor;

    /**
     * 状态  预留
     */
    private Integer state;


}
