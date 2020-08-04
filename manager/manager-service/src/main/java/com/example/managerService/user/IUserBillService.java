package com.example.managerService.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.UserBill;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-08-01
 */
public interface IUserBillService extends IService<UserBill> {

    /**
     * 用户账单列表
     * @param map
     * @return
     */
    HashMap<String,Object> getUserBillList(HashMap<String,Object> map);

    /**
     * 根据用户id获取
     * @param map
     * @return
     */
    HashMap<String,Object> getUserBillById(HashMap<String,Object> map);
}
