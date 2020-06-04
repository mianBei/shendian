package com.example.managerService.doctor;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.doctor.entity.PDoctor;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-05-07
 */
public interface IPDoctorService extends IService<PDoctor> {

    /**
     * 添加医生
     * @param hm
     * @return
     */
    int insertDoctor(HashMap<String,Object> hm);

    /**
     * 修改医生
     * @param hm
     * @return
     */
    int updateDoctor(HashMap<String,Object> hm);
}
