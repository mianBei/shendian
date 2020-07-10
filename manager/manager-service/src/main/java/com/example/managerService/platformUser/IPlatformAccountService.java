package com.example.managerService.platformUser;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.PlatformAccount;

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
}
