package com.example.managerService.shop;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.shop.entity.ShopBill;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-28
 */
public interface IShopBillService extends IService<ShopBill> {

    /**
     * 商品订单详情
     * @param map
     * @return
     */
    HashMap<String,Object> getShopBillList(HashMap<String,Object> map);

    /**
     * 根据id查询商品订单
     * @param map
     * @return
     */
    HashMap<String,Object> getShopBillById(HashMap<String,Object> map);
}
