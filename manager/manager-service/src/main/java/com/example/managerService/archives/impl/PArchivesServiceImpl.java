package com.example.managerService.archives.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.archives.entity.PArchives;
import com.example.managerDao.archives.mapper.PArchivesMapper;
import com.example.managerService.archives.IPArchivesService;
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
public class PArchivesServiceImpl extends ServiceImpl<PArchivesMapper, PArchives> implements IPArchivesService {

}
