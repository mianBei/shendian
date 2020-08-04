package com.example.managerService.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.StringUtils;
import com.example.managerDao.user.entity.UserTask;
import com.example.managerDao.user.mapper.UserTaskMapper;
import com.example.managerService.platform.IPlatformTaskService;
import com.example.managerService.user.IUserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-08-03
 */
@Service
public class UserTaskServiceImpl extends ServiceImpl<UserTaskMapper, UserTask> implements IUserTaskService {

    @Autowired
    UserTaskMapper taskMapper;
    @Autowired
    IPlatformTaskService platformTaskService;
    /**
     * 任务记录列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getTaskList(HashMap<String, Object> map) {

        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        IPage<Map<String,Object>> basicInfoList = taskMapper.selectMapsPage(pageN,new LambdaQueryWrapper<UserTask>()
                .orderByDesc(UserTask::getAddTime)
        );
        List<Map<String,Object>> recordList = basicInfoList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",basicInfoList.getTotal());
        return resultMap;
    }

    /**
     * 根据id查询任务详情
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getTaskById(HashMap<String, Object> map) {
        String userTaskId = map.get("userTaskId").toString();
        HashMap<String,Object> resultMap = new HashMap<>();
        UserTask task = taskMapper.selectById(userTaskId);
        resultMap.put("task",task);
        map.put("taskId",task.getBindTaskId());
        HashMap<String,Object> platformTaskMap = platformTaskService.getTaskById(map);
        resultMap.put("platformTask",platformTaskMap);
        return resultMap;
    }
}
