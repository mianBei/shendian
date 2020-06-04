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
@TableName("p_archives_update")
public class PArchivesUpdate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关联档案
     */
    private Integer archivesId;

    /**
     * 病情
     */
    private String condition;

    /**
     * 描述
     */
    private String describe;

    /**
     * 意见
     */
    private String opinion;

    private LocalDateTime createTime;

    /**
     * 结果
     */
    private String result;

    /**
     * 图片
     */
    private String picture;

    /**
     * 备注
     */
    private String note;

    /**
     * 1)已审核 0）未审核 关联字典
     */
    private Integer state;


}
