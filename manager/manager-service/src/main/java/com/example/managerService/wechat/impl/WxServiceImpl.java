package com.example.managerService.wechat.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.bean.Constants;
import com.example.common.wxutil.AesCbcUtil;
import com.example.common.wxutil.CommonUtil;
import com.example.common.wxutil.WxConfig;
import com.example.managerDao.doctor.entity.PDoctor;
import com.example.managerDao.doctor.mapper.PDoctorMapper;
import com.example.managerDao.user.entity.PUser;
import com.example.managerDao.user.mapper.PUserMapper;
import com.example.managerService.doctor.IPDoctorService;
import com.example.managerService.user.IPUserService;
import com.example.managerService.wechat.IWxService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
@Slf4j
public class WxServiceImpl implements IWxService {
    @Autowired
    PUserMapper pUserMapper;
    @Autowired
    PDoctorMapper pDoctorMapper;
    @Autowired
    IPUserService userService;
    @Autowired
    IPDoctorService doctorService;
    /**
     * 小程序授权接口
     *  1）查看用户信息数据
     * @param hm
     * @return
     */
    @Override
    public HashMap<String, Object> wxAuthorization(HashMap<String, Object> hm) {
        HashMap<String,Object> resultMap = new HashMap<>();
        //用户类型(userType)判断是医生(doctor)还是正常用户(user)
        String userType = hm.get("userType").toString();
        Object uid = hm.get("uid");
        if (uid!=null && !"".equals(uid)){
            int id = Integer.parseInt(uid.toString());
            if ("user".equals(userType)) {
                PUser user = pUserMapper.selectById(id);
                hm.put("data", user);
            } else {
                PDoctor doctor = pDoctorMapper.selectById(id);
                hm.put("data",doctor);
            }
        }else{
            resultMap = wxAuthByMiniProgram(hm);
        }
        return resultMap;
    }

    /**
     * 获取sessionKey
     * @param hm
     * @return
     */
    @Override
    public HashMap<String, Object> wxSession(HashMap<String, Object> hm) {
        getSessionKey(hm);
        return hm;
    }

    /**
     * 更改手机号
     * @param hm
     * @return
     */
    @Override
    public int updateMobile(HashMap<String, Object> hm) {
        getSessionKey(hm);
        String resultStr = null;
        int result = 0;
        try {
            resultStr = AesCbcUtil.decrypt(hm.get("encryptedData").toString(), hm.get("login_key").toString(), hm.get("iv").toString(), "UTF-8");
            JSONObject userInfoJSON = JSONObject.fromObject(resultStr);
            HashMap<String,Object> map = new HashMap<>();
            String phone = userInfoJSON.getString("phoneNumber");
            map.put("phone",phone);
            map.put("id",hm.get("uid"));
            String userType = hm.get("userType").toString();
            if ("user".equals(userType)){
                result = userService.updateUser(map);
            }else{
                result = doctorService.updateDoctor(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 小程序授权
     * 2）程序授权
     * @param hm
     */
    public HashMap<String, Object> wxAuthByMiniProgram(HashMap<String,Object> hm){
        Object data = hm.get("data");
        HashMap<String,Object> resultMap = new HashMap<>();
        if (data!=null){
            hm.put("status", Constants.RESULT_SUCCESS);
            hm.put("info",Constants.RESULT_SUCCESS_CON);
        }else{
            //进行小程序授权
            try {
                //getSessionKey(hm);
                log.info("---小程序调试用户参数---"+hm.toString());
                HashMap<String,Object> member = new HashMap<>();
                member = decrypt(hm);
                if(null != member){
                    //新增用户
                    String userType = hm.get("userType").toString();
                    String openId = member.get("openId").toString();
                    //查询用户是否存在
                    int userCount = pUserMapper.selectCount(new LambdaQueryWrapper<PUser>()
                        .eq(PUser::getOpenid,openId)
                    );
                    if ("user".equals(userType)){
                        if (userCount==0){
                            userService.insertUser(member);
                        }
                        PUser userEntity = pUserMapper.selectOne(new LambdaQueryWrapper<PUser>()
                            .eq(PUser::getOpenid,openId)
                        );
                        resultMap.put("data",userEntity);
                    }else{
                        if (userCount==0){
                            doctorService.insertDoctor(member);
                        }
                        PDoctor doctorEntity = pDoctorMapper.selectOne(new LambdaQueryWrapper<PDoctor>()
                            .eq(PDoctor::getOpenid,openId)
                        );
                        resultMap.put("data",doctorEntity);
                    }
                    resultMap.put("status",Constants.RESULT_SUCCESS);
                    resultMap.put("info",Constants.RESULT_SUCCESS_CON);
                }
            }catch (Exception e){
                e.printStackTrace();
                resultMap.put("status",Constants.RESULT_ERROR);
                resultMap.put("info",Constants.RESULT_ERROR_CON);
            }
        }
        return resultMap;
    }
    /**
     * 根据code获取open_id session_key
     * @param map
     */
    public void getSessionKey(HashMap<String,Object> map){
        String access_token = WxConfig.MINI_OATH_URL+WxConfig.APP_MINI_ID+"&secret="+ WxConfig.APP_MINI_SECRET+"&js_code="+ map.get("code")+"&grant_type=authorization_code";
        String result = CommonUtil.httpsRequest(access_token,"GET",null);
        log.info("微信授权返回参数-------"+result);
        JSONObject jsonObject = JSONObject.fromObject(result);
        String session_key = jsonObject.get("session_key").toString();
        String openid = (String) jsonObject.get("openid");
        map.put("mini_open_id",openid);
        map.put("session_key",session_key);
        if (jsonObject.containsKey("unionid")){
            if (jsonObject.get("unionid")!=null){
                map.put("union_id",jsonObject.getString("unionid"));
            }
        }
    }
    //微信用户信息解密
    private HashMap<String,Object> decrypt(HashMap<String,Object> param){
        HashMap<String,Object> userInfo = new HashMap<>();
        try {
            String resultStr = AesCbcUtil.decrypt(param.get("encryptedData").toString(), param.get("login_key").toString(), param.get("iv").toString(), "UTF-8");
            log.info("encryptedData解析返回参数=="+resultStr);
            if (null != resultStr && resultStr.length() > 0) {
                JSONObject userInfoJSON = JSONObject.fromObject(resultStr);
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                if (userInfoJSON.containsKey("unionId")){
                    userInfo.put("union_id", userInfoJSON.get("unionId"));
                }else{
                    userInfo.put("union_id", "");
                }
                return userInfo;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
