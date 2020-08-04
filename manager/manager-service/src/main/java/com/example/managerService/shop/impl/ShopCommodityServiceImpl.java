package com.example.managerService.shop.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.StringUtils;
import com.example.common.util.Util;
import com.example.managerDao.shop.entity.ShopCommodity;
import com.example.managerDao.shop.mapper.ShopCommodityMapper;
import com.example.managerService.shop.IShopCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
public class ShopCommodityServiceImpl extends ServiceImpl<ShopCommodityMapper, ShopCommodity> implements IShopCommodityService {

    @Autowired
    ShopCommodityMapper commodityMapper;
    @Value("${pic.url_prefix}")
    private String url_prefix;

    /**
     * 查看商品列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getCommodityList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String commodityName = map.get("commodityName").toString();
        IPage<Map<String,Object>> basicInfoList = commodityMapper.selectMapsPage(pageN,new LambdaQueryWrapper<ShopCommodity>()
                .like(org.apache.commons.lang.StringUtils.isNotBlank(commodityName),ShopCommodity::getCommodityName,commodityName)
                .orderByDesc(ShopCommodity::getAddTime)
        );
        List<Map<String,Object>> recordList = basicInfoList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
            String img = recordMap.get("commoditySubImage").toString();
            List<String> pics = Util.getWholePictureArrayPath(img,"",",",url_prefix);
            recordMap.put("commoditySubImage",pics.get(0));
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",basicInfoList.getTotal());
        return resultMap;
    }

    /**
     * 根据id查询商品详情
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getCommodityById(HashMap<String, Object> map) {
        Object commodityId = map.get("commodityId");
        HashMap<String,Object> resultMap = new HashMap<>();
        ShopCommodity commodity = new ShopCommodity();
        if (!"0".equals(commodityId)){
            commodity = commodityMapper.selectById(commodityId.toString());
            String img = commodity.getCommoditySubImage();
            String name = img.substring(img.lastIndexOf("/",img.lastIndexOf("/")-1));
            resultMap.put("picName",name);
            List<String> pics = Util.getWholePictureArrayPath(img,"",",",url_prefix);
            commodity.setCommoditySubImage(pics.get(0));
        }
        resultMap.put("commodity",commodity);
        return resultMap;
    }

    /**
     * 修改商品
     * @param map
     * @return
     */
    @Override
    public int saveCommodity(HashMap<String, Object> map) {
        Object commodityId = map.get("commodityId");
        int result=0;
        ShopCommodity commodity = new ShopCommodity();
        Object platformId = map.get("platformId");
        if (platformId !=null){
            commodity.setPlatformId(Integer.parseInt(platformId.toString()));
        }
        commodity.setBindShopId(Integer.parseInt(map.get("bindShopId").toString()));
        commodity.setCommoditySubImage(map.get("commoditySubImage").toString());
        commodity.setCommodityName(map.get("commodityName").toString());
        commodity.setCommodityAMT(BigDecimal.valueOf(Double.parseDouble(map.get("commodityAMT").toString())));
        commodity.setCommodityWorth(BigDecimal.valueOf(Double.parseDouble(map.get("commodityWorth").toString())));
        commodity.setCommodityStar(Integer.parseInt(map.get("commodityStar").toString()));
        commodity.setUploaderShopAccountId(Long.valueOf(map.get("uploaderShopAccountId").toString()));
        commodity.setUploaderShopAuth(Integer.parseInt(map.get("uploaderShopAuth").toString()));
        commodity.setUploaderUserId(map.get("uploaderUserId").toString());
        commodity.setIsDel(Integer.parseInt(map.get("isDel").toString()));
        commodity.setAddTime(Long.toString(System.currentTimeMillis()/1000L));
        if (commodityId==null || "".equals(commodityId)){
            result = commodityMapper.insert(commodity);
        }else{
            commodity.setCommodityId(Long.valueOf(commodityId.toString()));
            result = commodityMapper.updateById(commodity);
        }
        return result;
    }
}
