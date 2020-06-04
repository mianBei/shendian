package com.example.managerService.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.PUser;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-05-07
 */
public interface IPUserService extends IService<PUser> {

    /**
     * 添加用户
     * @param hm
     * @return
     */
    int insertUser(HashMap<String,Object> hm);

    /**
     * 修改用户
     * @param hm
     * @return
     */
    int updateUser(HashMap<String,Object> hm);
}
