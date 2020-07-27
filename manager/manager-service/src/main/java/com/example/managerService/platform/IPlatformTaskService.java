package com.example.managerService.platform;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.platform.entity.PlatformTask;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-24
 */
public interface IPlatformTaskService extends IService<PlatformTask> {

    /**
     * 任务列表
     * @param map
     * @return
     */
    HashMap<String,Object> getTaskList(HashMap<String,Object> map);

    /**
     * 根据id查询
     * @param map
     * @return
     */
    HashMap<String,Object> getTaskById(HashMap<String,Object> map);

    /**
     * 添加任务
     * @param map
     * @return
     */
    int saveTask(HashMap<String,Object> map);

    /**
     * 根据id删除
     * @param map
     * @return
     */
    int delTask(HashMap<String,Object> map);
}
