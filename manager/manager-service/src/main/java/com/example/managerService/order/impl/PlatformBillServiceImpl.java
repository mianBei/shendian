package com.example.managerService.order.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.StringUtils;
import com.example.managerDao.order.entity.PlatformBill;
import com.example.managerDao.order.mapper.PlatformBillMapper;
import com.example.managerDao.shop.entity.ShopBasicInfo;
import com.example.managerDao.shop.mapper.ShopBasicInfoMapper;
import com.example.managerDao.user.entity.UserAccountInfo;
import com.example.managerDao.user.mapper.UserAccountInfoMapper;
import com.example.managerService.order.IPlatformBillService;
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
 * @since 2020-07-22
 */
@Service
public class PlatformBillServiceImpl extends ServiceImpl<PlatformBillMapper, PlatformBill> implements IPlatformBillService {

    @Autowired
    PlatformBillMapper billMapper;
    @Autowired
    ShopBasicInfoMapper basicInfoMapper;
    @Autowired
    UserAccountInfoMapper accountInfoMapper;
    /**
     * 账单分页
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getBillList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String billTitle = map.get("billTitle").toString();
        String billCode = map.get("billCode").toString();
        IPage<Map<String,Object>> billList = billMapper.selectMapsPage(pageN,new LambdaQueryWrapper<PlatformBill>()
                .like(org.apache.commons.lang.StringUtils.isNotBlank(billTitle),PlatformBill::getBillTitle,billTitle)
                .eq(org.apache.commons.lang.StringUtils.isNotBlank(billCode),PlatformBill::getBillCode,billCode)
                .orderByDesc(PlatformBill::getAddTime)
        );
        List<Map<String,Object>> recordList = billList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",billList.getTotal());
        return resultMap;
    }

    /**
     * 根据id查询账单
     * @param map
     * @return
     */
    @Override
    public HashMap<String,Object> getBillById(HashMap<String, Object> map) {
        HashMap<String,Object> resultMap = new HashMap<>();
        int billId = Integer.parseInt(map.get("billId").toString());
        PlatformBill bill = billMapper.selectById(billId);
        resultMap.put("bill",bill);
        int shopId = bill.getBindShopId();
        if (shopId!=0){
            resultMap.put("shop",basicInfoMapper.selectById(shopId));
        }
        String userId = bill.getBindUserId();
        if (userId!=null && !"".equals(userId)){
            UserAccountInfo userAccountInfo = accountInfoMapper.selectOne(new LambdaQueryWrapper<UserAccountInfo>()
                    .eq(UserAccountInfo::getUserId,userId)
            );
            resultMap.put("user",userAccountInfo);
        }
        return resultMap;
    }
}
