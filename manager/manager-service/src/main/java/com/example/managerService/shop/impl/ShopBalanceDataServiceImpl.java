package com.example.managerService.shop.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.shop.entity.ShopBalanceData;
import com.example.managerDao.shop.mapper.ShopBalanceDataMapper;
import com.example.managerService.shop.IShopBalanceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-27
 */
@Service
public class ShopBalanceDataServiceImpl extends ServiceImpl<ShopBalanceDataMapper, ShopBalanceData> implements IShopBalanceDataService {
    @Autowired
    ShopBalanceDataMapper balanceDataMapper;
    /**
     * 根据id查询
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getBalanceById(HashMap<String, Object> map) {
        HashMap<String,Object> resultMap = new HashMap<>();
        int shopId = Integer.parseInt(map.get("shopId").toString());
        resultMap.put("balance",balanceDataMapper.selectById(shopId));
        return resultMap;
    }
}
