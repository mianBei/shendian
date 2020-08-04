package com.example.managerDao.city.entity;

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
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PlatformCity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 省市区名称
     */
    private String name;

    /**
     * 上级ID
     */
    private Integer parentid;

    /**
     * 简称
     */
    private String shortname;

    /**
     * 级别:0,中国；1，省分；2，市；3，区、县
     */
    private Integer leveltype;

    /**
     * 城市代码
     */
    private String citycode;

    /**
     * 邮编
     */
    private String zipcode;

    /**
     * 经度
     */
    private String lng;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 拼音
     */
    private String pinyin;

    private String status;


}
