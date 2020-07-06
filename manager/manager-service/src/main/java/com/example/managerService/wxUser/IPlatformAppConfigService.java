package com.example.managerService.wxUser;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.wxUser.entity.PlatformAppConfig;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-05
 */
public interface IPlatformAppConfigService extends IService<PlatformAppConfig> {

    /**
     * 微信授权接口
     * @param map
     * @return
     */
    HashMap<String,Object> wxAuthorization(HashMap<String,Object> map);
}
