package com.example.managerService.platformUser.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.user.entity.PlatformRole;
import com.example.managerDao.user.mapper.PlatformRoleMapper;
import com.example.managerService.platformUser.IPlatformRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Service
public class PlatformRoleServiceImpl extends ServiceImpl<PlatformRoleMapper, PlatformRole> implements IPlatformRoleService {
    @Autowired
    PlatformRoleMapper platformRoleMapper;

    /**
     * 根据id获取角色信息
     * @param roleId
     * @return
     */
    @Override
    public PlatformRole getRoleById(int roleId) {
        PlatformRole platformRole = platformRoleMapper.selectOne(new LambdaQueryWrapper<PlatformRole>()
                .eq(PlatformRole::getRoleId,roleId)
        );
        return platformRole;
    }
}
