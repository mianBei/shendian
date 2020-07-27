package com.example.managerService.shop.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.shop.entity.ShopHelpGratisEvent;
import com.example.managerDao.shop.mapper.ShopHelpGratisEventMapper;
import com.example.managerService.shop.IShopHelpGratisEventService;
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
public class ShopHelpGratisEventServiceImpl extends ServiceImpl<ShopHelpGratisEventMapper, ShopHelpGratisEvent> implements IShopHelpGratisEventService {

    @Autowired
    ShopHelpGratisEventMapper helpGratisEventMapper;
    /**
     * 根据id查询助力免单
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getHelpGratisEventById(HashMap<String, Object> map) {
        HashMap<String,Object> resultMap = new HashMap<>();
        int helpGratisEvent = Integer.parseInt(map.get("helpGratisEventId").toString());
        resultMap.put("helpGratisEvent",helpGratisEventMapper.selectById(helpGratisEvent));
        return resultMap;
    }
}
