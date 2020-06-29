package com.example.managerService.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.user.entity.AccountInfo;
import com.example.managerDao.user.mapper.AccountInfoMapper;
import com.example.managerService.user.IAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-06-29
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper, AccountInfo> implements IAccountInfoService {
    @Autowired
    AccountInfoMapper accountInfoMapper;
    @Override
    public List<AccountInfo> getAccountList() {

        return accountInfoMapper.selectList(null);
    }
}
