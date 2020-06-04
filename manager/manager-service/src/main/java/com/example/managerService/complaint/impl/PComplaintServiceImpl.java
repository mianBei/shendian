package com.example.managerService.complaint.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.managerDao.complaint.entity.PComplaint;
import com.example.managerDao.complaint.mapper.PComplaintMapper;
import com.example.managerService.complaint.IPComplaintService;
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
public class PComplaintServiceImpl extends ServiceImpl<PComplaintMapper, PComplaint> implements IPComplaintService {

}
