package com.example.managercontroller.user;

import com.example.common.bean.Constants;
import com.example.common.superCon.SuperController;
import com.example.common.util.Util;
import com.example.managerService.user.IUserAccountInfoService;
import com.example.managerService.user.IUserComplaintService;
import com.example.managerService.user.IUserDealLogService;
import com.example.managerService.user.IUserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RequestMapping("/user")
public class UserController extends SuperController{

    @Autowired
    IUserAccountInfoService accountInfoService;
    @Autowired
    IUserComplaintService complaintService;
    @Autowired
    IUserDealLogService dealLogService;

    @Autowired
    IUserTaskService taskService;

    /**
     * 跳转用户列表
     * @return
     */
    @RequestMapping(value = "/userAccount.htm",method = RequestMethod.GET)
    public String userAccount(){
        return "user/userAccount/userAccount";
    }

    /**
     * 用户列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getUserAccountList.htm",method = RequestMethod.GET)
    public void getUserAccountList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap =accountInfoService.getUserAccountList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据id查询用户信息
     * @return
     */
    @RequestMapping(value = "/selectUserAccount.htm" ,method = RequestMethod.GET)
    public String selectUserAccount(HttpServletRequest request){
        request.setAttribute("userAccount",accountInfoService.getMapUserById(Util.genHmParam(request)));
        return "user/userAccount/selectUserAccount";
    }

    /**
     * 跳转用户投诉
     * @return
     */
    @RequestMapping(value = "/userComplaint.htm",method = RequestMethod.GET)
    public String userComplaint(){
        return "user/complaint/complaint";
    }

    /**
     * 投诉列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getComplaintList.htm",method = RequestMethod.GET)
    public void getComplaintList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap =complaintService.getComplaintList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据id查询投诉内容
     * @return
     */
    @RequestMapping(value = "/goSaveComplaint.htm" ,method = RequestMethod.GET)
    public String goSaveComplaint(HttpServletRequest request){
        request.setAttribute("complaint",complaintService.getComplaintById(Util.genHmParam(request)));
        return "user/complaint/saveComplaint";
    }

    /**
     * 修改投诉
     * @param request
     * @param response
     */
    @RequestMapping(value = "/saveComplaint.htm",method = RequestMethod.GET)
    public void saveComplaint(HttpServletRequest request, HttpServletResponse response){
        HashMap<String,Object> resultMap = new HashMap<>();
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            int result = complaintService.saveComplaint(hm);
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
     * 跳转用户交易记录
     * @return
     */
    @RequestMapping(value = "/dealLog.htm",method = RequestMethod.GET)
    public String dealLog(){
        return "user/dealLog/dealLog";
    }

    /**
     * 交易列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getDealLogList.htm",method = RequestMethod.GET)
    public void getDealLogList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap = dealLogService.getDealLogList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 跳转用户任务
     * @return
     */
    @RequestMapping(value = "/task.htm",method = RequestMethod.GET)
    public String task(){
        return "user/task/task";
    }

    /**
     * 任务记录列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getTaskList.htm",method = RequestMethod.GET)
    public void getTaskList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap = taskService.getTaskList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据id查询任务记录
     * @return
     */
    @RequestMapping(value = "/selectTask.htm" ,method = RequestMethod.GET)
    public String selectTask(HttpServletRequest request){
        request.setAttribute("task",taskService.getTaskById(Util.genHmParam(request)));
        return "user/task/selectTask";
    }
}
