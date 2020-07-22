package com.example.managerService.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.order.entity.PlatformBill;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-22
 */
public interface IPlatformBillService extends IService<PlatformBill> {

    /**
     * 账单分页
     * @param map
     * @return
     */
    HashMap<String,Object> getBillList(HashMap<String,Object> map);

    /**
     * 根据id查询账单
     * @param map
     * @return
     */
    HashMap<String,Object> getBillById(HashMap<String,Object> map);
}
