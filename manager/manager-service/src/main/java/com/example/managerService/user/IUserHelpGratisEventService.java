package com.example.managerService.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.UserHelpGratisEvent;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-23
 */
public interface IUserHelpGratisEventService extends IService<UserHelpGratisEvent> {

    /**
     * 根据id查询用户免单
     * @param map
     * @return
     */
    HashMap<String,Object> getUserHelpGratisEventById(HashMap<String,Object> map);
}
