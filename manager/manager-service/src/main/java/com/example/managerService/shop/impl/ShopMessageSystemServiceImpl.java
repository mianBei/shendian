package com.example.managerService.shop.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.DateUtils;
import com.example.common.util.StringUtils;
import com.example.managerDao.shop.entity.ShopMessageSystem;
import com.example.managerDao.shop.mapper.ShopMessageSystemMapper;
import com.example.managerService.shop.IShopMessageSystemService;
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
 * @since 2020-07-30
 */
@Service
public class ShopMessageSystemServiceImpl extends ServiceImpl<ShopMessageSystemMapper, ShopMessageSystem> implements IShopMessageSystemService {

    @Autowired
    ShopMessageSystemMapper messageSystemMapper;
    /**
     * 商家消息列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getMessageSystemList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String messageTitle = map.get("messageTitle").toString();
        String bindShopId = map.get("bindShopId").toString();
        IPage<Map<String,Object>> basicInfoList = messageSystemMapper.selectMapsPage(pageN,new LambdaQueryWrapper<ShopMessageSystem>()
                .like(org.apache.commons.lang.StringUtils.isNotBlank(messageTitle),ShopMessageSystem::getMessageTitle,messageTitle)
                .eq(org.apache.commons.lang.StringUtils.isNotBlank(bindShopId),ShopMessageSystem::getBindShopId,bindShopId)
                .orderByDesc(ShopMessageSystem::getMessageTime)
        );
        List<Map<String,Object>> recordList = basicInfoList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
            String messageTime = recordMap.get("messageTime").toString();
            recordMap.put("messageTime",DateUtils.stampToDate(messageTime));
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",basicInfoList.getTotal());
        return resultMap;
    }
}
