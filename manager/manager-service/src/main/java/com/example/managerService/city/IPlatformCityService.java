package com.example.managerService.city;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.city.entity.PlatformCity;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-28
 */
public interface IPlatformCityService extends IService<PlatformCity> {

    /**
     * 城市列表
     * @param map
     * @return
     */
    List<PlatformCity> getCityByMap(HashMap<String,Object> map);
}
