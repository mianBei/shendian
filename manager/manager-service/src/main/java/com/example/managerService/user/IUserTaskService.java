package com.example.managerService.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.UserTask;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-08-03
 */
public interface IUserTaskService extends IService<UserTask> {

    /**
     * 任务记录列表
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
}
