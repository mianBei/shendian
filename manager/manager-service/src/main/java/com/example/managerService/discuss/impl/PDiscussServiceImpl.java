package com.example.managerService.discuss.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.discuss.entity.PDiscuss;
import com.example.managerDao.discuss.mapper.PDiscussMapper;
import com.example.managerService.discuss.IPDiscussService;
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
public class PDiscussServiceImpl extends ServiceImpl<PDiscussMapper, PDiscuss> implements IPDiscussService {

}
