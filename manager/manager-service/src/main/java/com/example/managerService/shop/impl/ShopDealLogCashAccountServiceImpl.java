package com.example.managerService.shop.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.DateUtils;
import com.example.common.util.StringUtils;
import com.example.managerDao.shop.entity.ShopDealLogCashAccount;
import com.example.managerDao.shop.mapper.ShopDealLogCashAccountMapper;
import com.example.managerService.shop.IShopDealLogCashAccountService;
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
 * @since 2020-07-29
 */
@Service
public class ShopDealLogCashAccountServiceImpl extends ServiceImpl<ShopDealLogCashAccountMapper, ShopDealLogCashAccount> implements IShopDealLogCashAccountService {

    @Autowired
    ShopDealLogCashAccountMapper dealLogCashAccountMapper;

    /**
     * 交易记录列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getCashLogList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String dealLogTitle = map.get("dealLogTitle").toString();
        IPage<Map<String,Object>> basicInfoList = dealLogCashAccountMapper.selectMapsPage(pageN,new LambdaQueryWrapper<ShopDealLogCashAccount>()
                .like(org.apache.commons.lang.StringUtils.isNotBlank(dealLogTitle),ShopDealLogCashAccount::getDealLogTitle,dealLogTitle)
                .orderByDesc(ShopDealLogCashAccount::getAddTime)
        );
        List<Map<String,Object>> recordList = basicInfoList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
            recordMap.put("addTime",DateUtils.stampToDate(recordMap.get("addTime").toString()));
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",basicInfoList.getTotal());
        return resultMap;
    }

    /**
     * 根据id查询交易记录
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getCashLogById(HashMap<String, Object> map) {
        HashMap<String,Object> resultMap = new HashMap<>();
        ShopDealLogCashAccount dealLogCashAccount = dealLogCashAccountMapper.selectById(map.get("cashAccountDealLogId").toString());
        resultMap.put("cashLog",dealLogCashAccount);
        return resultMap;
    }
}
