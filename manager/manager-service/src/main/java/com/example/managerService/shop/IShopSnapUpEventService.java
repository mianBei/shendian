package com.example.managerService.shop;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.shop.entity.ShopSnapUpEvent;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-23
 */
public interface IShopSnapUpEventService extends IService<ShopSnapUpEvent> {

    /**
     * 根据id查询抢购
     * @param map
     * @return
     */
    HashMap<String,Object> getSnapUpEventById(HashMap<String,Object> map);
}
