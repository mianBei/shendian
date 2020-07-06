package com.example.managerService.wxUser.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.wxUser.entity.OpenIdKey;
import com.example.managerDao.wxUser.mapper.OpenIdKeyMapper;
import com.example.managerService.wxUser.IOpenIdKeyService;
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
public class OpenIdKeyServiceImpl extends ServiceImpl<OpenIdKeyMapper, OpenIdKey> implements IOpenIdKeyService {

}
