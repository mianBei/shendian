package com.example.managerDao.archives.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("p_archives_drug")
public class PArchivesDrug implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 药物名称
     */
    private String name;

    /**
     * 药物剂量
     */
    private Integer metering;

    /**
     * 注意事项
     */
    private String careful;

    /**
     * 关联医生
     */
    private Integer doctorId;

    /**
     * 关联用户
     */
    private Integer userId;

    /**
     * 用药方法
     */
    private String method;


}
