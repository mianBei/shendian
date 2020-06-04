package com.example.managerDao.archives.entity;

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
public class PArchives implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关联宠物
     */
    private Integer petId;

    /**
     * 关联订单
     */
    private Integer orderId;

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

    private LocalDateTime updateTime;

    /**
     * 结果
     */
    private String result;

    /**
     * 图片
     */
    private String picture;

    /**
     * 关联药物开方表  p_archives_drug
     */
    private Integer archivesDrugId;

    /**
     * 档案编号
     */
    private String archivesNumber;


}
