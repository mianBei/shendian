package com.example.managerService.shop;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.shop.entity.ShopDealLogCashAccount;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-29
 */
public interface IShopDealLogCashAccountService extends IService<ShopDealLogCashAccount> {
    /**
     * 交易记录列表
     * @param map
     * @return
     */
    HashMap<String,Object> getCashLogList(HashMap<String,Object> map);

    /**
     * 根据id查询交易记录
     * @param map
     * @return
     */
    HashMap<String,Object> getCashLogById(HashMap<String,Object> map);
}
