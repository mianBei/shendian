package com.example.managerService.shop;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.shop.entity.ShopBasicInfo;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-22
 */
public interface IShopBasicInfoService extends IService<ShopBasicInfo> {
    /**
     * 查看商家信息列表
     * @param map
     * @return
     */
    HashMap<String,Object> getBasicInfoList(HashMap<String,Object> map);

    /**
     * 根据id查询
     * @param map
     * @return
     */
    HashMap<String,Object> getBasicInfoById(HashMap<String,Object> map);
}
