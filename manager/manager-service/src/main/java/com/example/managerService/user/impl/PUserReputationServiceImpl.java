package com.example.managerService.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.user.entity.PUserReputation;
import com.example.managerDao.user.mapper.PUserReputationMapper;
import com.example.managerService.user.IPUserReputationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-05-07
 */
@Service
public class PUserReputationServiceImpl extends ServiceImpl<PUserReputationMapper, PUserReputation> implements IPUserReputationService {

}
