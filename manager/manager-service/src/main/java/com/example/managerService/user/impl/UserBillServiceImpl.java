package com.example.managerService.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.StringUtils;
import com.example.managerDao.user.entity.UserBill;
import com.example.managerDao.user.mapper.UserBillMapper;
import com.example.managerService.shop.IShopBasicInfoService;
import com.example.managerService.user.IUserBillService;
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
 * @since 2020-08-01
 */
@Service
public class UserBillServiceImpl extends ServiceImpl<UserBillMapper, UserBill> implements IUserBillService {

    @Autowired
    UserBillMapper billMapper;
    @Autowired
    IShopBasicInfoService basicInfoService;
    /**
     * 用户账单列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getUserBillList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String billTitle = map.get("billTitle").toString();
        String billCode = map.get("billCode").toString();
        IPage<Map<String,Object>> basicInfoList = billMapper.selectMapsPage(pageN,new LambdaQueryWrapper<UserBill>()
                .like(org.apache.commons.lang.StringUtils.isNotBlank(billTitle),UserBill::getBillTitle,billTitle)
                .eq(org.apache.commons.lang.StringUtils.isNotBlank(billCode),UserBill::getBillCode,billCode)
                .orderByDesc(UserBill::getAddTime)
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
     * 根据id查询用户账单详情
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getUserBillById(HashMap<String, Object> map) {
        Object billId = map.get("billId");
        HashMap<String,Object> resultMap = new HashMap<>();
        UserBill bill = new UserBill();
        if (billId!=null){
            bill = billMapper.selectById(billId.toString());
        }
        resultMap.put("bill",bill);
        int shopId = bill.getBindShopId();
        if (shopId!=0){
            map.put("shopId",shopId);
            resultMap.put("shop",basicInfoService.getBasicInfoById(map));
        }
        return resultMap;
    }
}
