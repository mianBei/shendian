package com.example.managerService.platform;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.platform.entity.PlatformPaymentConfig;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-24
 */
public interface IPlatformPaymentConfigService extends IService<PlatformPaymentConfig> {

    /**
     * 付款配置列表
     * @param map
     * @return
     */
    HashMap<String,Object> getPaymentConfigList(HashMap<String,Object> map);
    /**
     * 根据id查询付款设置
     * @return
     */
    PlatformPaymentConfig getPaymentConfig();

    /**
     * 修改付款配置
     * @param map
     * @return
     */
    int savePaymentConfig(HashMap<String,Object> map);
}
