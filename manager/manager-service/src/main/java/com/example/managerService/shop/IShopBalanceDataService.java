package com.example.managerService.shop;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.shop.entity.ShopBalanceData;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-27
 */
public interface IShopBalanceDataService extends IService<ShopBalanceData> {

    /**
     * 根据id查询
     * @param map
     * @return
     */
    HashMap<String,Object> getBalanceById(HashMap<String,Object> map);
}
