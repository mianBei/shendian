package com.example.managercontroller.platform;

import com.example.common.superCon.SuperController;
import com.example.common.util.Util;
import com.example.managerService.platform.IPlatformBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * 平台信息管理
 */
@Controller
@RequestMapping("/platform")
public class PlatformController extends SuperController{

    @Autowired
    IPlatformBasicInfoService basicInfoService;
    /**
     * 跳转平台信息管理
     * @return
     */
    @RequestMapping(value = "/basicInfo.htm" ,method = RequestMethod.GET)
    public String basicInfo(){
        return "platform/basicInfo";
    }

    /**
     * 平台信息列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getBaseInfoList.htm",method = RequestMethod.GET)
    public void getBaseInfoList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap = basicInfoService.getBasicInfoList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 跳转修改平台信息
     * @return
     */
    @RequestMapping(value = "/goSaveBasicInfo.htm" ,method = RequestMethod.GET)
    public String goSaveBasicInfo(HttpServletRequest request){
        HashMap<String,Object> hm = Util.genHmParam(request);
        request.setAttribute("basicInfo",basicInfoService.getBasicInfoById(hm));
        return "platform/saveBasicInfo";
    }

    /**
     * 修改平台信息
     */
    public void saveBasicInfo(){

    }
}
