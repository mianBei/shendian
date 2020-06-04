package com.example.managercontroller.wechat;

import com.example.common.bean.Constants;
import com.example.common.superCon.SuperController;
import com.example.common.util.Util;
import com.example.managerDao.user.entity.PUser;
import com.example.managerService.doctor.IPDoctorService;
import com.example.managerService.wechat.IWxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/wx/wx-user")
@Api(description = "微信相关接口")
public class wxController extends SuperController{
    @Autowired
    IWxService wxService;
    @ApiOperation(value = "微信授权",notes = "查询是否授权没有则进行授权，有就返回用户信息")
    @RequestMapping(value = "/wxAuthorization.htm",method = RequestMethod.POST)
    public HashMap<String,Object> wxAuthorization(HttpServletRequest request){

        return wxService.wxAuthorization(Util.genHmParam(request));
    }
    @ApiOperation(value = "获取session",notes = "获取sessionKey")
    @RequestMapping(value = "/wxSession.htm",method = RequestMethod.GET)
    public HashMap<String, Object> wxSession(HttpServletRequest request){
        HashMap<String,Object> resultMap = new HashMap<>();
        try {
            HashMap<String,Object> sessionMap = wxService.wxSession(Util.genHmParam(request));
            resultMap.put("status", Constants.RESULT_SUCCESS);
            resultMap.put("info",Constants.RESULT_SUCCESS_CON);
            resultMap.put("data",sessionMap);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("status", Constants.RESULT_ERROR);
            resultMap.put("info",Constants.RESULT_ERROR_CON);
        }
        return resultMap;
    }
    @ApiOperation(value = "修改手机号",notes = "获取用户手机号")
    @RequestMapping(value = "/updateMobile.htm",method = RequestMethod.POST)
    public HashMap<String, Object> updateMobile(HttpServletRequest request){
        HashMap<String,Object> resultMap = new HashMap<>();
        try {
            int result = wxService.updateMobile(Util.genHmParam(request));
            resultMap.put("status", result);
            resultMap.put("info",Constants.RESULT_SUCCESS_CON);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("status", Constants.RESULT_ERROR);
            resultMap.put("info",Constants.RESULT_ERROR_CON);
        }
        return resultMap;
    }
}
