package com.example.managerService.platform.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.DateUtils;
import com.example.common.util.StringUtils;
import com.example.common.util.Util;
import com.example.managerDao.platform.entity.PlatformTask;
import com.example.managerDao.platform.mapper.PlatformTaskMapper;
import com.example.managerService.platform.IPlatformTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * @since 2020-07-24
 */
@Service
public class PlatformTaskServiceImpl extends ServiceImpl<PlatformTaskMapper, PlatformTask> implements IPlatformTaskService {
    @Value("${pic.url_prefix}")
    private String url_prefix;

    @Autowired
    PlatformTaskMapper taskMapper;
    /**
     * 任务列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getTaskList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String taskName = map.get("taskName").toString();
        IPage<Map<String,Object>> messageSystemList = taskMapper.selectMapsPage(pageN,new LambdaQueryWrapper<PlatformTask>()
                .like(org.apache.commons.lang.StringUtils.isNotBlank(taskName),PlatformTask::getTaskName,taskName)
        );
        List<Map<String,Object>> recordList = messageSystemList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
            String taskIcon = recordMap.get("taskIcon").toString();
            List<String> pics = Util.getWholePictureArrayPath(taskIcon,"",",",url_prefix);
            recordMap.put("taskIcon",pics.get(0));
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",messageSystemList.getTotal());
        return resultMap;
    }

    /**
     * 根据id查询任务
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getTaskById(HashMap<String, Object> map) {
        Object taskId = map.get("taskId");
        HashMap<String,Object> resultMap = new HashMap<>();
        PlatformTask task = new PlatformTask();
        String name = "";
        if (!"0".equals(taskId)){
            task = taskMapper.selectById(Integer.parseInt(taskId.toString()));
            String logo = task.getTaskIcon();
            name = logo.substring(logo.lastIndexOf("/",logo.lastIndexOf("/")-1));

            List<String> pics = Util.getWholePictureArrayPath(logo,"",",",url_prefix);
            task.setTaskIcon(pics.get(0));
        }
        resultMap.put("picName",name);
        resultMap.put("task",task);
        return resultMap;
    }

    /**
     * 修改任务
     * @param map
     * @return
     */
    @Override
    public int saveTask(HashMap<String, Object> map) {
        int result=0;
        Object taskId = map.get("taskId");
        PlatformTask task = new PlatformTask();
        task.setTaskType(Integer.parseInt(map.get("taskType").toString()));
        task.setTaskName(map.get("taskName").toString());
        task.setTaskIcon(map.get("taskIcon").toString());
        Object taskIndate = map.get("taskIndate");
        if (taskIndate!=null && !"".equals(taskIndate)){
            task.setTaskIndate(Integer.parseInt(taskIndate.toString()));
        }
        Object taskTarget = map.get("taskTarget");
        if (taskTarget!=null && !"".equals(taskTarget)){
            task.setTaskTarget(Integer.parseInt(map.get("taskTarget").toString()));
        }
        if (taskId==null || "".equals(taskId)){
            result = taskMapper.insert(task);
        }else{
            task.setTaskId(Integer.parseInt(taskId.toString()));
            result = taskMapper.updateById(task);
        }
        return result;
    }

    /**
     * 根据id删除
     * @param map
     * @return
     */
    @Override
    public int delTask(HashMap<String, Object> map) {
        int taskId = Integer.parseInt(map.get("taskId").toString());
        int result = taskMapper.deleteById(taskId);
        return result;
    }
}
