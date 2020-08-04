package com.example.managerService.shop.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.bean.Constants;
import com.example.common.util.DateUtils;
import com.example.common.util.StringUtils;
import com.example.managerDao.shop.entity.ShopBasicInfo;
import com.example.managerDao.shop.mapper.ShopBasicInfoMapper;
import com.example.managerService.city.IPlatformCityService;
import com.example.managerService.shop.IShopBasicInfoService;
import net.sf.json.JSONArray;
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
public class ShopBasicInfoServiceImpl extends ServiceImpl<ShopBasicInfoMapper, ShopBasicInfo> implements IShopBasicInfoService {
    @Autowired
    ShopBasicInfoMapper basicInfoMapper;
    @Autowired
    IPlatformCityService cityService;

    /**
     * 查询商家信息列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getBasicInfoList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String shopName = map.get("shopName").toString();
        IPage<Map<String,Object>> basicInfoList = basicInfoMapper.selectMapsPage(pageN,new LambdaQueryWrapper<ShopBasicInfo>()
                .like(org.apache.commons.lang.StringUtils.isNotBlank(shopName),ShopBasicInfo::getShopName,shopName)
                .orderByDesc(ShopBasicInfo::getShopEnterTime)
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
     * 根据id查询
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getBasicInfoById(HashMap<String, Object> map) {
        HashMap<String,Object> resultMap = new HashMap<>();
        ShopBasicInfo basicInfo = basicInfoMapper.selectOne(new LambdaQueryWrapper<ShopBasicInfo>()
                .eq(ShopBasicInfo::getShopId,map.get("shopId"))
        );
        //时间戳转换
        basicInfo.setShopEnterTime(DateUtils.stampToDate(basicInfo.getShopEnterTime()));
        int isConfig = basicInfo.getIsConfig();
        if (isConfig==1){
            basicInfo.setConfigTime(DateUtils.stampToDate(basicInfo.getConfigTime()));
        }
        //轮播图
        String banner = basicInfo.getShopBanner();
        resultMap.put("banner",JSONArray.fromObject(banner));
        resultMap.put("basicInfo",basicInfo);
        return resultMap;
    }
}
