package com.example.managerService.platformUser.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.user.entity.PlatformUserRole;
import com.example.managerDao.user.mapper.PlatformUserRoleMapper;
import com.example.managerService.platformUser.IPlatformUserRoleService;
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
public class PlatformUserRoleServiceImpl extends ServiceImpl<PlatformUserRoleMapper, PlatformUserRole> implements IPlatformUserRoleService {

}
