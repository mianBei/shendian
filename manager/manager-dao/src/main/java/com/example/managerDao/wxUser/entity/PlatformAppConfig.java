package com.example.managerDao.wxUser.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PlatformAppConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台id
     */
    @TableField("platformId")
    private Integer platformId;

    /**
     * 平台每一端配置id  主键
     */
    @TableId(value = "platformAppId", type = IdType.AUTO)
    private Integer platformAppId;

    /**
     * 程序类型
      1：app；2：微信小程序
     */
    @TableField("appType")
    private Integer appType;

    /**
     * 使用者类型 
     1：用户端和商家端；3：平台端
     */
    @TableField("useType")
    private Integer useType;

    /**
     * 程序配置数据（json字符串）：
微信小程序为：
{
   appId：
   appSecret：
}
     */
    @TableField("appData")
    private String appData;

    /**
     * 备注
     */
    private String remark;


}
