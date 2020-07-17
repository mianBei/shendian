package com.example.managerService.platform;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.platform.entity.PlatformBasicInfo;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-15
 */
public interface IPlatformBasicInfoService extends IService<PlatformBasicInfo> {

    /**
     * 平台信息列表
     * @param map
     * @return
     */
    HashMap<String,Object> getBasicInfoList(HashMap<String,Object> map);

    /**
     * 根据id查看平台信息
     * @param map
     * @return
     */
    PlatformBasicInfo getBasicInfoById(HashMap<String,Object> map);

    int saveBasicInfo(HashMap<String,Object> map);
}
