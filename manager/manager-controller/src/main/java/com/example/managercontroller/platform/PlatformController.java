package com.example.managercontroller.platform;

import com.example.common.bean.Constants;
import com.example.common.superCon.SuperController;
import com.example.common.util.Util;
import com.example.managerDao.jurisdiction.entity.PlatformAccount;
import com.example.managerDao.platform.entity.PlatformBasicInfo;
import com.example.managerDao.platform.entity.PlatformMessagePost;
import com.example.managerService.platform.IPlatformBasicInfoService;
import com.example.managerService.platform.IPlatformMessagePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    IPlatformMessagePostService messagePostService;
    /**
     * 跳转平台信息管理
     * @return
     */
    @RequestMapping(value = "/basicInfo.htm" ,method = RequestMethod.GET)
    public String basicInfo(){
        return "platform/basicInfo/basicInfo";
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
        HashMap<String,Object> basicInfo = basicInfoService.getBasicInfoById(hm);
        request.setAttribute("basicInfo",basicInfo.get("basicInfo"));
        request.setAttribute("picName",basicInfo.get("picName"));
        return "platform/basicInfo/saveBasicInfo";
    }

    /**
     * 修改平台信息
     */
    @RequestMapping(value = "saveBasicInfo.htm",method = RequestMethod.POST)
    public void saveBasicInfo(HttpServletRequest request,HttpServletResponse response){
        HashMap<String,Object> resultMap = new HashMap<>();
        try{
            HashMap<String,Object> hm = Util.genHmParam(request);
            int result = basicInfoService.saveBasicInfo(hm);
            resultMap.put("status",result);
            resultMap.put("info", Constants.RESULT_SUCCESS_CON);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("status",Constants.RESULT_ERROR);
            resultMap.put("info", Constants.RESULT_ERROR_CON);
        }
        outJsonForMap(resultMap,response);
    }

    /**
     * 跳转信息发布
     * @return
     */
    @RequestMapping(value = "/messagePost.htm" ,method = RequestMethod.GET)
    public String messagePost(){
        return "platform/messagePost/messagePost";
    }

    /**
     * 平台信息发布列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getMessagePostList.htm",method = RequestMethod.GET)
    public void getMessagePostList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap = messagePostService.getMessagePostList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 跳转修改发送信息
     * @return
     */
    @RequestMapping(value = "/goSaveMessagePost.htm" ,method = RequestMethod.GET)
    public String goSaveMessagePost(HttpServletRequest request){
        HashMap<String,Object> hm = Util.genHmParam(request);
        HashMap<String,Object> messagePost = messagePostService.getMessagePostById(hm);
        request.setAttribute("messagePost",messagePost.get("messagePost"));
        request.setAttribute("picName",messagePost.get("picName"));
        return "platform/messagePost/saveMessagePost";
    }

    /**
     * 添加发送消息
     */
    @RequestMapping(value = "saveMessagePost.htm",method = RequestMethod.POST)
    public void saveMessagePost(HttpServletRequest request,HttpServletResponse response){
        HashMap<String,Object> resultMap = new HashMap<>();
        try{
            HashMap<String,PlatformAccount> userBean=(HashMap)request.getSession().getAttribute("userSession");
            PlatformAccount accountInfo = userBean.get("accountInfo");
            HashMap<String,Object> hm = Util.genHmParam(request);
            hm.put("platformId",accountInfo.getPlatformId());
            hm.put("platformAccountUserId",accountInfo.getPlatformAccountUserId());
            int result = messagePostService.saveMessagePost(hm);
            resultMap.put("status",result);
            resultMap.put("info", Constants.RESULT_SUCCESS_CON);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("status",Constants.RESULT_ERROR);
            resultMap.put("info", Constants.RESULT_ERROR_CON);
        }
        outJsonForMap(resultMap,response);
    }

    /**
     * 删除消息
     */
    @RequestMapping(value = "delMessagePost.htm",method = RequestMethod.POST)
    public void delMessagePost(HttpServletRequest request,HttpServletResponse response){
        HashMap<String,Object> resultMap = new HashMap<>();
        try{
            HashMap<String,Object> hm = Util.genHmParam(request);
            int result = messagePostService.delMessagePost(hm);
            resultMap.put("status",result);
            resultMap.put("info", Constants.RESULT_SUCCESS_CON);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("status",Constants.RESULT_ERROR);
            resultMap.put("info", Constants.RESULT_ERROR_CON);
        }
        outJsonForMap(resultMap,response);
    }

    /**
     * 跳转修改发送信息
     * @return
     */
    @RequestMapping(value = "/selectMessagePost.htm" ,method = RequestMethod.GET)
    public String selectMessagePost(HttpServletRequest request){
        HashMap<String,Object> hm = Util.genHmParam(request);
        HashMap<String,Object> messagePost = messagePostService.getMessagePostById(hm);
        request.setAttribute("messagePost",messagePost.get("messagePost"));
        request.setAttribute("picName",messagePost.get("picName"));
        request.setAttribute("arrList",messagePost.get("arrList"));
        return "platform/messagePost/selectMessagePost";
    }
}
