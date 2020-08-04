package com.example.managerService.platform.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.StringUtils;
import com.example.managerDao.platform.entity.SmsTemplate;
import com.example.managerDao.platform.mapper.SmsTemplateMapper;
import com.example.managerService.platform.ISmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-30
 */
@Service
public class SmsTemplateServiceImpl extends ServiceImpl<SmsTemplateMapper, SmsTemplate> implements ISmsTemplateService {

    @Autowired
    SmsTemplateMapper smsTemplateMapper;
    /**
     * 短信模板列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getSmsList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        IPage<Map<String,Object>> messagePostList = smsTemplateMapper.selectMapsPage(pageN,null);
        List<Map<String,Object>> recordList = messagePostList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",messagePostList.getTotal());
        return resultMap;
    }

    /**
     * 根据id获取信息
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getSmsById(HashMap<String, Object> map) {
        Object smsId = map.get("SMSId");
        HashMap<String,Object> resultMap = new HashMap<>();
        SmsTemplate smsTemplate = new SmsTemplate();
        if (!"0".equals(smsId)){
            smsTemplate = smsTemplateMapper.selectById(Integer.parseInt(smsId.toString()));
        }
        resultMap.put("sms",smsTemplate);
        return resultMap;
    }

    /**
     * 修改短信
     * @param map
     * @return
     */
    @Override
    public int saveSms(HashMap<String, Object> map) {
        Object SMSId = map.get("SMSId");
        int result = 0;
        SmsTemplate smsTemplate = new SmsTemplate();
        smsTemplate.setSMSType(1);
        smsTemplate.setSMSSDKAppId(map.get("SMSSDKAppId").toString());
        smsTemplate.setSMSAppKey(map.get("SMSAppKey").toString());
        smsTemplate.setSMSTemplateId(map.get("SMSTemplateId").toString());
        smsTemplate.setSMSSign(map.get("SMSSign").toString());
        smsTemplate.setSMSContent(map.get("SMSContent").toString());
        if (SMSId==null ||"".equals(SMSId)){
            result = smsTemplateMapper.insert(smsTemplate);
        }else{
            smsTemplate.setSMSId(Integer.parseInt(SMSId.toString()));
            result = smsTemplateMapper.updateById(smsTemplate);
        }
        return result;
    }
}
