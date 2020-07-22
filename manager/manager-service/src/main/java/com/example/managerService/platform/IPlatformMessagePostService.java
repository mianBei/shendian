package com.example.managerService.platform;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.platform.entity.PlatformMessagePost;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-20
 */
public interface IPlatformMessagePostService extends IService<PlatformMessagePost> {

    /**
     * 平台消息列表
     * @param map
     * @return
     */
    HashMap<String,Object> getMessagePostList(HashMap<String,Object> map);

    /**
     * 根据id查询对象
     * @param map
     * @return
     */
    HashMap<String,Object> getMessagePostById(HashMap<String,Object> map);

    /**
     * 修改平台消息
     * @param map
     * @return
     */
    int saveMessagePost(HashMap<String,Object> map);

    /**
     * 删除发布消息
     * @param map
     * @return
     */
    int delMessagePost(HashMap<String,Object> map);
}
