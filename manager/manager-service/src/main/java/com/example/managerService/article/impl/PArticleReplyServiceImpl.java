package com.example.managerService.article.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.article.entity.PArticleReply;
import com.example.managerDao.article.mapper.PArticleReplyMapper;
import com.example.managerService.article.IPArticleReplyService;
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
public class PArticleReplyServiceImpl extends ServiceImpl<PArticleReplyMapper, PArticleReply> implements IPArticleReplyService {

}
