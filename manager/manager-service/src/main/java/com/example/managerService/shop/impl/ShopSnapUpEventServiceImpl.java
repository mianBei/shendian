package com.example.managerService.shop.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.shop.entity.ShopSnapUpEvent;
import com.example.managerDao.shop.mapper.ShopSnapUpEventMapper;
import com.example.managerService.shop.IShopSnapUpEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-23
 */
@Service
public class ShopSnapUpEventServiceImpl extends ServiceImpl<ShopSnapUpEventMapper, ShopSnapUpEvent> implements IShopSnapUpEventService {

    @Autowired
    ShopSnapUpEventMapper snapUpEventMapper;

    /**
     * 根据id查询
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getSnapUpEventById(HashMap<String, Object> map) {
        HashMap<String,Object> resultMap = new HashMap<>();
        int snapUpEventId = Integer.parseInt(map.get("snapUpEventId").toString());
        resultMap.put("snapUpEvent",snapUpEventMapper.selectById(snapUpEventId));
        return resultMap;
    }
}
