package com.example.managerService.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.order.entity.PlatformOrder;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-23
 */
public interface IPlatformOrderService extends IService<PlatformOrder> {
    /**
     * 订单列表
     * @param map
     * @return
     */
    HashMap<String,Object> getOrderList(HashMap<String,Object> map);

    /**
     * 查看详情
     * @param map
     * @return
     */
    HashMap<String,Object> getOrderById(HashMap<String,Object> map);
}
