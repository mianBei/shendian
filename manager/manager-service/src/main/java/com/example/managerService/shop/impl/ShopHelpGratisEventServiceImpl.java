package com.example.managerService.shop.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.DateUtils;
import com.example.common.util.StringUtils;
import com.example.managerDao.shop.entity.ShopHelpGratisEvent;
import com.example.managerDao.shop.mapper.ShopHelpGratisEventMapper;
import com.example.managerService.shop.IShopHelpGratisEventService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-23
 */
@Service
public class ShopHelpGratisEventServiceImpl extends ServiceImpl<ShopHelpGratisEventMapper, ShopHelpGratisEvent> implements IShopHelpGratisEventService {

    @Autowired
    ShopHelpGratisEventMapper helpGratisEventMapper;
    /**
     * 根据id查询助力免单
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getHelpGratisEventById(HashMap<String, Object> map) {
        HashMap<String,Object> resultMap = new HashMap<>();
        int helpGratisEvent = Integer.parseInt(map.get("helpGratisEventId").toString());
        ShopHelpGratisEvent helpGratisEvent1 = new ShopHelpGratisEvent();
        if (helpGratisEvent!=0){
            helpGratisEvent1 = helpGratisEventMapper.selectById(helpGratisEvent);
        }
        resultMap.put("helpGratisEvent",helpGratisEvent1);
        return resultMap;
    }

    /**
     * 查询助力免单列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getHelpGratisList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String helpGratisEventName = map.get("helpGratisEventName").toString();
        IPage<Map<String,Object>> basicInfoList = helpGratisEventMapper.selectMapsPage(pageN,new LambdaQueryWrapper<ShopHelpGratisEvent>()
                .like(org.apache.commons.lang.StringUtils.isNotBlank(helpGratisEventName),ShopHelpGratisEvent::getHelpGratisEventName,helpGratisEventName)
                .orderByDesc(ShopHelpGratisEvent::getAddTime)
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
     * 修改助力免单
     * @param map
     * @return
     */
    @SneakyThrows
    @Override
    public int saveHelpGratis(HashMap<String, Object> map) {
        int result=0;
        Object helpGratisId = map.get("helpGratisEventId");
        ShopHelpGratisEvent helpGratisEvent = new ShopHelpGratisEvent();
        helpGratisEvent.setBindShopId(Integer.parseInt(map.get("bindShopId").toString()));
        helpGratisEvent.setHelpGratisEventName(map.get("helpGratisEventName").toString());
        helpGratisEvent.setNeedHelpNum(Integer.parseInt(map.get("needHelpNum").toString()));
        helpGratisEvent.setCommodityWorth(BigDecimal.valueOf(Double.parseDouble(map.get("commodityWorth").toString())));
        helpGratisEvent.setGratisAMT(BigDecimal.valueOf(Double.parseDouble(map.get("gratisAMT").toString())));
        helpGratisEvent.setHelpGratisEventIndate(Integer.parseInt(map.get("helpGratisEventIndate").toString()));
        String startTime = map.get("startTime").toString();
        if (startTime.contains(":")){
            helpGratisEvent.setStartTime(Long.valueOf(DateUtils.Date2TimeStamp(startTime,"yyyy-MM-dd HH:mm:ss")));
            helpGratisEvent.setEndTime(Long.valueOf(DateUtils.Date2TimeStamp(map.get("endTime").toString(),"yyyy-MM-dd HH:mm:ss")));
        }else{
            helpGratisEvent.setStartTime(Long.valueOf(startTime));
            helpGratisEvent.setEndTime(Long.valueOf(map.get("endTime").toString()));
        }

        int eventStateNum = Integer.parseInt(map.get("eventStateNum").toString());
        if (eventStateNum==1){
            helpGratisEvent.setEventState("未开始");
        }else if(eventStateNum==2){
            helpGratisEvent.setEventState("已开始");
        }else if(eventStateNum==3){
            helpGratisEvent.setEventState("已过期");
        }
        helpGratisEvent.setEventStateNum(eventStateNum);
        helpGratisEvent.setCommodityId(Integer.parseInt(map.get("commodityId").toString()));
        if (helpGratisId==null || "".equals(helpGratisId)){
            result = helpGratisEventMapper.insert(helpGratisEvent);
        }else{
            helpGratisEvent.setHelpGratisEventId(Long.valueOf(helpGratisId.toString()));
            result = helpGratisEventMapper.updateById(helpGratisEvent);
        }
        return result;
    }
}
