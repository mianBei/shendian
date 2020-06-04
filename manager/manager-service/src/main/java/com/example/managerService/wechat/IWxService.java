package com.example.managerService.wechat;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * 微信相关接口
 */
public interface IWxService {
    /**
     * 小程序授权
     * @param hm
     * @return
     */
    HashMap<String,Object> wxAuthorization(HashMap<String,Object> hm);

    /**
     * 获取sessionKey
     * @param hm
     * @return
     */
    HashMap<String,Object> wxSession(HashMap<String,Object> hm);

    /**
     * 更改用户手机号
     * @param hm
     * @return
     */
    int updateMobile(HashMap<String,Object> hm);
}
