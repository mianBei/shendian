package com.example.managerService.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.UserComplaint;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-08-03
 */
public interface IUserComplaintService extends IService<UserComplaint> {
    /**
     * 获取投诉列表
     * @param map
     * @return
     */
    HashMap<String,Object> getComplaintList(HashMap<String,Object> map);

    /**
     * 根据id获取
     * @param map
     * @return
     */
    HashMap<String,Object> getComplaintById(HashMap<String,Object> map);

    /**
     * 修改投诉列表
     * @param map
     * @return
     */
    int saveComplaint(HashMap<String,Object> map);
}
