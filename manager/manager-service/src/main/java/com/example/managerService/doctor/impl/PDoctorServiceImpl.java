package com.example.managerService.doctor.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.bean.Constants;
import com.example.managerDao.doctor.entity.PDoctor;
import com.example.managerDao.doctor.mapper.PDoctorMapper;
import com.example.managerService.doctor.IPDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-05-07
 */
@Service
public class PDoctorServiceImpl extends ServiceImpl<PDoctorMapper, PDoctor> implements IPDoctorService {
    @Autowired
    PDoctorMapper doctorMapper;
    /**
     * 添加医生用户
     * @param hm
     * @return
     */
    @Override
    public int insertDoctor(HashMap<String, Object> hm) {
        PDoctor doctor = new PDoctor();
        doctor.setOpenid(hm.get("openId").toString());
        doctor.setHeadPortrait(hm.get("avatarUrl").toString());
        doctor.setSex((int)hm.get("gender"));
        doctor.setName(hm.get("nickName").toString());
        doctor.setCreateTime(LocalDateTime.now());
        doctor.setReputation(Constants.REPUTATION);
        return doctorMapper.insert(doctor);
    }

    /**
     * 修改医生信息
     * @param hm
     * @return
     */
    @Override
    public int updateDoctor(HashMap<String, Object> hm) {
        PDoctor doctor = new PDoctor();
        doctor.setId(hm.get("id").toString());
        doctor.setPhone(hm.get("phone").toString());
        return doctorMapper.updateById(doctor);
    }
}
