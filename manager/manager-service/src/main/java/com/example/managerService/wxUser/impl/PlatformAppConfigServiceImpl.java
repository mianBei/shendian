package com.example.managerService.wxUser.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.bean.Constants;
import com.example.common.util.JSONUtils;
import com.example.common.wxutil.CommonUtil;
import com.example.managerDao.wxUser.entity.OpenIdKey;
import com.example.managerDao.wxUser.entity.PlatformAppConfig;
import com.example.managerDao.wxUser.mapper.OpenIdKeyMapper;
import com.example.managerDao.wxUser.mapper.PlatformAppConfigMapper;
import com.example.managerService.wxUser.IPlatformAppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-05
 */
@Service
public class PlatformAppConfigServiceImpl extends ServiceImpl<PlatformAppConfigMapper, PlatformAppConfig> implements IPlatformAppConfigService {
    @Autowired
    PlatformAppConfigMapper platformAppConfigMapper;
    /*@Autowired
    PlatformBasicInfoMapper platformBasicInfoMapper;*/
    @Autowired
    OpenIdKeyMapper openIdKeyMapper;
    /**
     * 微信授权
     * 获取appid和appSecret
     * 结合code获取openid
     * 获取平台信息
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> wxAuthorization(HashMap<String, Object> map) {

        PlatformAppConfig platformAppConfig = platformAppConfigMapper.selectOne(new LambdaQueryWrapper<PlatformAppConfig>()
                .eq(PlatformAppConfig::getAppType, Constants.APPTYPE)
                .eq(PlatformAppConfig::getUseType,Constants.USERTYPE)
        );

        //获取appData数据里的appId和appSecret
        String appData = platformAppConfig.getAppData();
        Map<String,String> appDataMap = JSONUtils.json2map(appData);
        map.put("appId",appDataMap.get("appId"));
        map.put("appSecret",appDataMap.get("appSecret"));

        CommonUtil.getSessionKey(map);

        HashMap<String,Object> resultMap = new HashMap<>();

        String isUser = verificationUser(map);
        /*PlatformBasicInfo platformBasicInfo = platformBasicInfoMapper.selectOne(new LambdaQueryWrapper<PlatformBasicInfo>()
                .eq(PlatformBasicInfo::getPlatformId,platformAppConfig.getPlatformId())
        );*/
        resultMap.put("isUser",isUser);
        resultMap.put("openid",map.get("openid"));
        /*resultMap.put("platformName",platformBasicInfo.getPlatformName());
        resultMap.put("platformLogo",platformBasicInfo.getPlatformLogo());
        resultMap.put("platformTel",platformBasicInfo.getPlatformTel());*/
        return resultMap;
    }

    /**
     * 验证信息
     */
    public String verificationUser(HashMap<String,Object> map){
        String openid = map.get("openid").toString();
        //判断openID是否有用户存在,没有则添加
        OpenIdKey openIdKey = openIdKeyMapper.selectOne(new LambdaQueryWrapper<OpenIdKey>()
                .eq(OpenIdKey::getOpenId,openid)
        );
        OpenIdKey openIdKey1 = new OpenIdKey();
        String isUser = Constants.USERYES;
        String session_key = map.get("session_key").toString();
        if (openIdKey==null){
            openIdKey1.setOpenId(openid);
            openIdKey1.setAppType(Constants.APPTYPE);
            openIdKey1.setSessionKey(session_key);
            openIdKeyMapper.insert(openIdKey1);
            isUser=Constants.USERNO;
        }
        //如果userId等于空那么更新openIdKey里面的sessionKey
        String userId = openIdKey.getUserId();
        if (userId==null&&userId==""){
            openIdKey1=new OpenIdKey();
            openIdKey1.setSessionKey(session_key);
            openIdKeyMapper.update(openIdKey1,new LambdaQueryWrapper<OpenIdKey>()
                    .eq(OpenIdKey::getAppType,Constants.APPTYPE)
                    .eq(OpenIdKey::getOpenId,openid)
            );
            isUser=Constants.USERNO;
        }

        return isUser;
    }
}
