package com.example.managerService.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.user.entity.UserBalanceData;
import com.example.managerDao.user.mapper.UserBalanceDataMapper;
import com.example.managerService.user.IUserBalanceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-08-01
 */
@Service
public class UserBalanceDataServiceImpl extends ServiceImpl<UserBalanceDataMapper, UserBalanceData> implements IUserBalanceDataService {

    @Autowired
    UserBalanceDataMapper balanceDataMapper;
    /**
     * 根据id查询用户余额
     * @param userId
     * @return
     */
    @Override
    public UserBalanceData getUserBalanceById(String userId) {

        return balanceDataMapper.selectOne(new LambdaQueryWrapper<UserBalanceData>()
                .eq(UserBalanceData::getUserId,userId)
        );
    }
}
