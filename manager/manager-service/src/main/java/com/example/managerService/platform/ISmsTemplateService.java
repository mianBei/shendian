package com.example.managerService.platform;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.platform.entity.SmsTemplate;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-30
 */
public interface ISmsTemplateService extends IService<SmsTemplate> {

    /**
     * 短信模板列表
     * @param map
     * @return
     */
    HashMap<String,Object> getSmsList(HashMap<String,Object> map);

    /**
     * 根据id获取详情
     * @param map
     * @return
     */
    HashMap<String,Object> getSmsById(HashMap<String,Object> map);

    /**
     * 修改短信
     * @param map
     * @return
     */
    int saveSms(HashMap<String,Object> map);
}
