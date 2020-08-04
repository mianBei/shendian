package com.example.managerService.shop;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.shop.entity.ShopCommodity;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-28
 */
public interface IShopCommodityService extends IService<ShopCommodity> {

    /**
     * 查看商品列表
     * @param map
     * @return
     */
    HashMap<String,Object> getCommodityList(HashMap<String,Object> map);

    /**
     * 根据id查询商品
     * @param map
     * @return
     */
    HashMap<String,Object> getCommodityById(HashMap<String,Object> map);

    /**
     * 修改商品
     * @param map
     * @return
     */
    int saveCommodity(HashMap<String,Object> map);
}
