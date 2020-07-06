package com.example.managerService.wxUser.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.wxUser.entity.PlatformBasicInfo;
import com.example.managerDao.wxUser.mapper.PlatformBasicInfoMapper;
import com.example.managerService.wxUser.IPlatformBasicInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-05
 */
@Service
public class PlatformBasicInfoServiceImpl extends ServiceImpl<PlatformBasicInfoMapper, PlatformBasicInfo> implements IPlatformBasicInfoService {

}
