package com.example.managerService.shop;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.shop.entity.ShopAccount;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-27
 */
public interface IShopAccountService extends IService<ShopAccount> {

    /**
     * 商家店铺账户
     * @param map
     * @return
     */
    HashMap<String,Object> getAccountList(HashMap<String,Object> map);

    /**
     * 商家用户信息
     * @param map
     * @return
     */
    HashMap<String,Object> getAccountUserById(HashMap<String,Object> map);
}
