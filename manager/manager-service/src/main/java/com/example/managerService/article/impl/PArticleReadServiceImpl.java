package com.example.managerService.article.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.article.entity.PArticleRead;
import com.example.managerDao.article.mapper.PArticleReadMapper;
import com.example.managerService.article.IPArticleReadService;
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
public class PArticleReadServiceImpl extends ServiceImpl<PArticleReadMapper, PArticleRead> implements IPArticleReadService {

}
