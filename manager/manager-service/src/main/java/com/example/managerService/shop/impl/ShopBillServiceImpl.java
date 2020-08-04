package com.example.managerService.shop.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.StringUtils;
import com.example.managerDao.shop.entity.ShopBill;
import com.example.managerDao.shop.mapper.ShopBillMapper;
import com.example.managerService.shop.IShopBasicInfoService;
import com.example.managerService.shop.IShopBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-28
 */
@Service
public class ShopBillServiceImpl extends ServiceImpl<ShopBillMapper, ShopBill> implements IShopBillService {

    @Autowired
    ShopBillMapper billMapper;
    @Autowired
    IShopBasicInfoService basicInfoService;
    /**
     * 商品订单类型
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getShopBillList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String billTitle = map.get("billTitle").toString();
        String billCode = map.get("billCode").toString();
        IPage<Map<String,Object>> basicInfoList = billMapper.selectMapsPage(pageN,new LambdaQueryWrapper<ShopBill>()
                .like(org.apache.commons.lang.StringUtils.isNotBlank(billTitle),ShopBill::getBillTitle,billTitle)
                .eq(org.apache.commons.lang.StringUtils.isNotBlank(billCode),ShopBill::getBillCode,billCode)
                .orderByDesc(ShopBill::getAddTime)
        );
        List<Map<String,Object>> recordList = basicInfoList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",basicInfoList.getTotal());
        return resultMap;
    }

    /**
     * 根据id查询商品订单
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getShopBillById(HashMap<String, Object> map) {
        HashMap<String,Object> resultMap = new HashMap<>();
        int billId = Integer.parseInt(map.get("billId").toString());
        ShopBill bill = billMapper.selectById(billId);
        resultMap.put("bill",bill);
        int shopId = bill.getBindShopId();
        if (shopId!=0){
            map.put("shopId",shopId);
            resultMap.put("shop",basicInfoService.getBasicInfoById(map));
        }
        return resultMap;
    }
}
