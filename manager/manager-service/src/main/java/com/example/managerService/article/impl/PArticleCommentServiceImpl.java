package com.example.managerService.article.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.article.entity.PArticleComment;
import com.example.managerDao.article.mapper.PArticleCommentMapper;
import com.example.managerService.article.IPArticleCommentService;
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
public class PArticleCommentServiceImpl extends ServiceImpl<PArticleCommentMapper, PArticleComment> implements IPArticleCommentService {

}
