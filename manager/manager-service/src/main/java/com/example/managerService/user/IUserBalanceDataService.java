package com.example.managerService.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.UserBalanceData;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-08-01
 */
public interface IUserBalanceDataService extends IService<UserBalanceData> {

    /**
     * 根据id查询用户余额
     * @param userId
     * @return
     */
    UserBalanceData getUserBalanceById(String userId);
}
