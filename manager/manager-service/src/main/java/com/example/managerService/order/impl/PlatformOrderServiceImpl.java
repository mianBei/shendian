package com.example.managerService.order.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.DateUtils;
import com.example.common.util.StringUtils;
import com.example.managerDao.order.entity.PlatformOrder;
import com.example.managerDao.order.mapper.PlatformOrderMapper;
import com.example.managerDao.platform.entity.PlatformMessageSystem;
import com.example.managerService.order.IPlatformOrderService;
import com.example.managerService.shop.IShopHelpGratisEventService;
import com.example.managerService.shop.IShopSnapUpEventService;
import com.example.managerService.user.IUserHelpGratisEventService;
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
 * @since 2020-07-23
 */
@Service
public class PlatformOrderServiceImpl extends ServiceImpl<PlatformOrderMapper, PlatformOrder> implements IPlatformOrderService {

    @Autowired
    PlatformOrderMapper orderMapper;
    @Autowired
    IShopSnapUpEventService snapUpEventService;
    @Autowired
    IShopHelpGratisEventService helpGratisEventService;
    @Autowired
    IUserHelpGratisEventService userHelpGratisEventService;
    /**
     * 订单列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getOrderList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String orderCode = map.get("orderCode").toString();
        IPage<Map<String,Object>> messageSystemList = orderMapper.selectMapsPage(pageN,new LambdaQueryWrapper<PlatformOrder>()
                .eq(org.apache.commons.lang.StringUtils.isNotBlank(orderCode),PlatformOrder::getOrderCode,orderCode)
                .orderByDesc(PlatformOrder::getOrderTime)
        );
        List<Map<String,Object>> recordList = messageSystemList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
            String messageTime = DateUtils.stampToDate(recordMap.get("orderTime").toString());
            recordMap.put("orderTime",messageTime);
            /*String paymentTime= DateUtils.stampToDate(recordMap.get("paymentTime").toString());
            recordMap.put("paymentTime",paymentTime);*/
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",messageSystemList.getTotal());
        return resultMap;
    }

    /**
     * 查看详情
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getOrderById(HashMap<String, Object> map) {
        HashMap<String,Object> resultMap = new HashMap<>();
        int orderId = Integer.parseInt(map.get("orderId").toString());
        PlatformOrder order = orderMapper.selectById(orderId);
        resultMap.put("order",order);
        //时间
        HashMap<String,Object> timeMap = new HashMap<>();
        timeMap.put("orderTime",DateUtils.stampToDate(order.getOrderTime().toString()));
        timeMap.put("paymentEndTime",DateUtils.stampToDate(order.getPaymentEndTime().toString()));
        Object paymentTime = order.getPaymentTime();
        if (paymentTime!=null && paymentTime!=""){
            timeMap.put("paymentTime",DateUtils.stampToDate(paymentTime.toString()));
        }
        timeMap.put("updateTime",DateUtils.stampToDate(order.getUpdateTime().toString()));
        Object inQueueTime = order.getInQueueTime();
        if (inQueueTime!=null && inQueueTime!=""){
            timeMap.put("inQueueTime",DateUtils.stampToDate(inQueueTime.toString()));
        }
        Object queueSucceedTime = order.getQueueSucceedTime();
        if (queueSucceedTime!=null && queueSucceedTime!=""){
            timeMap.put("QueueSucceedTime",DateUtils.stampToDate(queueSucceedTime.toString()));
        }
        Object quickRefunds = order.getQuickRefundsTime();
        if (quickRefunds!=null && quickRefunds!=""){
            timeMap.put("quickRefundsTime",DateUtils.stampToDate(quickRefunds.toString()));
        }
        resultMap.put("time",timeMap);
        //抢购活动
        HashMap<String,Object> hm = new HashMap<>();
        Object snapUpEvent = order.getSnapUpEventId();
        if (snapUpEvent!=null && snapUpEvent!=""){
            hm.put("snapUpEventId",snapUpEvent);
            HashMap<String,Object> snapUpEventMap = snapUpEventService.getSnapUpEventById(hm);
            resultMap.put("snapUpEvent",snapUpEventMap.get("snapUpEvent"));
        }else {
            resultMap.put("snapUpEvent",null);
        }
        //助力免单
        Object helpGratisEventId = order.getHelpGratisEventId();
        if (helpGratisEventId!=null && helpGratisEventId!=""){
            hm.put("helpGratisEventId",helpGratisEventId);
            HashMap<String,Object> helpGratisEventMap = helpGratisEventService.getHelpGratisEventById(hm);
            resultMap.put("helpGratisEvent",helpGratisEventMap.get("helpGratisEvent"));
        }else {
            resultMap.put("helpGratisEvent",null);
        }
        //用户助力免单
        Object userHelpGratisEventId = order.getUserHelpGratisEventId();
        if (userHelpGratisEventId!=null && userHelpGratisEventId!=""){
            hm.put("userHelpGratisEventId",userHelpGratisEventId);
            HashMap<String,Object> userHelpGratisEventMap = userHelpGratisEventService.getUserHelpGratisEventById(hm);
            resultMap.put("userHelpGratisEvent",userHelpGratisEventMap.get("userHelpGratisEvent"));
        }else{
            resultMap.put("userHelpGratisEvent",null);
        }

        return resultMap;
    }
}
