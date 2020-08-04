package com.example.managerService.city.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.city.entity.PlatformCity;
import com.example.managerDao.city.mapper.PlatformCityMapper;
import com.example.managerService.city.IPlatformCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-28
 */
@Service
public class PlatformCityServiceImpl extends ServiceImpl<PlatformCityMapper, PlatformCity> implements IPlatformCityService {

    @Autowired
    PlatformCityMapper cityMapper;

    /**
     * 城市列表
     * @param map
     * @return
     */
    @Override
    public List<PlatformCity> getCityByMap(HashMap<String, Object> map) {
        Object levelType = map.get("leveltype");
        Object parentId = map.get("parentid");
        List<PlatformCity> city = cityMapper.selectList(new LambdaQueryWrapper<PlatformCity>()
                .eq(org.apache.commons.lang.StringUtils.isNotBlank(levelType.toString()),PlatformCity::getLeveltype,levelType)
                .eq(org.apache.commons.lang.StringUtils.isNotBlank(parentId.toString()),PlatformCity::getParentid,parentId)
        );
        return city;
    }
}
