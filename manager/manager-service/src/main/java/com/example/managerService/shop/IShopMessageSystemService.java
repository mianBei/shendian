package com.example.managerService.shop;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.shop.entity.ShopMessageSystem;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-30
 */
public interface IShopMessageSystemService extends IService<ShopMessageSystem> {
    /**
     * 商家消息列表
     * @param map
     * @return
     */
    HashMap<String,Object> getMessageSystemList(HashMap<String,Object> map);
}
