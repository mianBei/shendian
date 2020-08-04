package com.example.managerService.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.UserDealLog;

import java.util.HashMap;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-08-03
 */
public interface IUserDealLogService extends IService<UserDealLog> {

    /**
     * 交易记录列表
     * @param map
     * @return
     */
    HashMap<String,Object> getDealLogList(HashMap<String,Object> map);
}
