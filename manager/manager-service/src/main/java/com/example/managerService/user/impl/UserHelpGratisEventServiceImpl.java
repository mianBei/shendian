package com.example.managerService.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.user.entity.UserHelpGratisEvent;
import com.example.managerDao.user.mapper.UserHelpGratisEventMapper;
import com.example.managerService.user.IUserHelpGratisEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-23
 */
@Service
public class UserHelpGratisEventServiceImpl extends ServiceImpl<UserHelpGratisEventMapper, UserHelpGratisEvent> implements IUserHelpGratisEventService {

    @Autowired
    UserHelpGratisEventMapper helpGratisEventMapper;
    /**
     * 根据id查询用户免单
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getUserHelpGratisEventById(HashMap<String, Object> map) {
        HashMap<String,Object> resultMap = new HashMap<>();
        int id = Integer.parseInt(map.get("userHelpGratisEventId").toString());
        resultMap.put("userHelpGratisEvent",helpGratisEventMapper.selectById(id));
        return resultMap;
    }
}
