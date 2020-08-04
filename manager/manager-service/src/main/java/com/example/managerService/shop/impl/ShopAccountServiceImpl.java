package com.example.managerService.shop.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.StringUtils;
import com.example.managerDao.shop.entity.ShopAccount;
import com.example.managerDao.shop.mapper.ShopAccountMapper;
import com.example.managerDao.user.entity.UserAccountInfo;
import com.example.managerService.shop.IShopAccountService;
import com.example.managerService.user.IUserAccountInfoService;
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
 * @since 2020-07-27
 */
@Service
public class ShopAccountServiceImpl extends ServiceImpl<ShopAccountMapper, ShopAccount> implements IShopAccountService {

    @Autowired
    ShopAccountMapper accountMapper;
    @Autowired
    IUserAccountInfoService accountInfoService;
    /**
     * 商家店铺商户
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getAccountList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String accountCode = map.get("shopAccountCode").toString();
        IPage<Map<String,Object>> basicInfoList = accountMapper.selectMapsPage(pageN,new LambdaQueryWrapper<ShopAccount>()
                .eq(org.apache.commons.lang.StringUtils.isNotBlank(accountCode),ShopAccount::getShopAccountCode,accountCode)
                .orderByDesc(ShopAccount::getAddTime)
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
     * 商家用户信息
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getAccountUserById(HashMap<String, Object> map) {
        String userId = map.get("userId").toString();
        UserAccountInfo accountInfo = accountInfoService.getUserById(userId);
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("user",accountInfo);
        return resultMap;
    }

    /**
     * 根据账号查询商家用户
     * @return
     */
    @Override
    public ShopAccount getAccountByCode(String code) {

        return accountMapper.selectOne(new LambdaQueryWrapper<ShopAccount>()
                .eq(ShopAccount::getShopAccountCode,code)
        );
    }
}
