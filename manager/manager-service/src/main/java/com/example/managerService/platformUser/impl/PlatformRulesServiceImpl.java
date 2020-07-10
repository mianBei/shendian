package com.example.managerService.platformUser.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.user.entity.PlatformRules;
import com.example.managerDao.user.mapper.PlatformRulesMapper;
import com.example.managerService.platformUser.IPlatformRulesService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Service
public class PlatformRulesServiceImpl extends ServiceImpl<PlatformRulesMapper, PlatformRules> implements IPlatformRulesService {

}
