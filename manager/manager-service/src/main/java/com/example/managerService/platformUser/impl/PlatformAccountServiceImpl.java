package com.example.managerService.platformUser.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.user.entity.PlatformAccount;
import com.example.managerDao.user.mapper.PlatformAccountMapper;
import com.example.managerService.platformUser.IPlatformAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Service
public class PlatformAccountServiceImpl extends ServiceImpl<PlatformAccountMapper, PlatformAccount> implements IPlatformAccountService {

    @Autowired
    PlatformAccountMapper platformAccountMapper;

    /**
     * 根据code获取用户信息
     * @param code
     * @return
     */
    @Override
    public PlatformAccount getAccountByCode(String code) {
        return platformAccountMapper.selectOne(new LambdaQueryWrapper<PlatformAccount>()
                .eq(PlatformAccount::getPlatformAccountCode,code)
        );
    }
}
