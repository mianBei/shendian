package com.example.managerService.discuss.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.discuss.entity.PDiscussComment;
import com.example.managerDao.discuss.mapper.PDiscussCommentMapper;
import com.example.managerService.discuss.IPDiscussCommentService;
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
public class PDiscussCommentServiceImpl extends ServiceImpl<PDiscussCommentMapper, PDiscussComment> implements IPDiscussCommentService {

}
