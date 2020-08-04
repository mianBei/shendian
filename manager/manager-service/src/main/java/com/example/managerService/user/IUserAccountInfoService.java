package com.example.managerService.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.UserAccountInfo;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-22
 */
public interface IUserAccountInfoService extends IService<UserAccountInfo> {

    /**
     * 根据id查询与用户
     * @param userId
     * @return
     */
    UserAccountInfo getUserById(String userId);

    /**
     * 用户列表
     * @param map
     * @return
     */
    HashMap<String,Object> getUserAccountList(HashMap<String,Object> map);

    /**
     * 根据id查询返回map类型
     * @param map
     * @return
     */
    HashMap<String,Object> getMapUserById(HashMap<String,Object> map);
}
