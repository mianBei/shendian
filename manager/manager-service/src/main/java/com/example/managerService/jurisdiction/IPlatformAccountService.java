package com.example.managerService.jurisdiction;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.PlatformAccount;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
public interface IPlatformAccountService extends IService<PlatformAccount> {
    /**
     * 根据code获取用户信息
     * @param code
     * @return
     */
    PlatformAccount getAccountByCode(String code);

    /**
     * 用户列表
     * @param map
     * @return
     */
    HashMap<String,Object> getAccountList(HashMap<String,Object> map);
}
