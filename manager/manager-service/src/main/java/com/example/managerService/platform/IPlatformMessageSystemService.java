package com.example.managerService.platform;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.platform.entity.PlatformMessageSystem;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-23
 */
public interface IPlatformMessageSystemService extends IService<PlatformMessageSystem> {

    /**
     * 查询未读数量
     * @return
     */
    int getMessageSystemCount();

    /**
     * 系统消息列表
     * @param map
     * @return
     */
    HashMap<String,Object> getMessageSystemList(HashMap<String,Object> map);
}
