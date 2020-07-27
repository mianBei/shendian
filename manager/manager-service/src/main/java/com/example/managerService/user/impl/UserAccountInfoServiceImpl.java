package com.example.managerService.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.user.entity.UserAccountInfo;
import com.example.managerDao.user.mapper.UserAccountInfoMapper;
import com.example.managerService.user.IUserAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-22
 */
@Service
public class UserAccountInfoServiceImpl extends ServiceImpl<UserAccountInfoMapper, UserAccountInfo> implements IUserAccountInfoService {

    @Autowired
    UserAccountInfoMapper accountInfoMapper;
    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    @Override
    public UserAccountInfo getUserById(int userId) {
        return accountInfoMapper.selectById(userId);
    }
}
