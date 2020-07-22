package com.example.managercontroller.jurisdiction;

import com.example.common.bean.Constants;
import com.example.common.superCon.SuperController;
import com.example.common.util.Util;
import com.example.managerService.jurisdiction.IPlatformRoleRuleService;
import com.example.managerService.jurisdiction.IPlatformRoleService;
import com.example.managerService.jurisdiction.IPlatformRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 */
@Controller
@RequestMapping("/rule")
public class RuleController extends SuperController{
    @Autowired
    IPlatformRoleService platformRoleService;
    @Autowired
    IPlatformRoleRuleService platformRoleRuleService;
    @Autowired
    IPlatformRuleService platformRuleService;
    /**
     * 角色页面跳转
     * @return
     */
    @RequestMapping(value = "/roleManage.htm" ,method = RequestMethod.GET)
    public String roleManage(){
        return "jurisdiction/roleManage";
    }

    /**
     * 角色列表查询
     * @param request
     * @return
     */
    @RequestMapping(value = "/getRoleList.htm" ,method = RequestMethod.GET)
    public void getRoleList(HttpServletRequest request,HttpServletResponse response){
        try {
            HashMap<String,Object> map = Util.genHmParam(request);
            outJsonForMap(platformRoleService.getRoleList(map),response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 修改或添加角色
     * @param request
     * @param response
     */
    @RequestMapping(value = "/saveRole.htm",method = RequestMethod.POST)
    public void saveRole(HttpServletRequest request, HttpServletResponse response){
        HashMap<String,Object> resultMap = new HashMap<>();
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            int result = platformRoleService.saveRole(hm);
            resultMap.put("status",result);
            resultMap.put("info",Constants.RESULT_SUCCESS_CON);

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status",Constants.RESULT_ERROR);
            resultMap.put("info",Constants.RESULT_ERROR_CON);
        }
        outJsonForMap(resultMap, response);
    }
    /**
     * 删除角色
     * @param request
     * @param response
     */
    @RequestMapping(value = "/delRole.htm",method = RequestMethod.POST)
    public void delRole(HttpServletRequest request, HttpServletResponse response){
        HashMap<String,Object> resultMap = new HashMap<>();
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            platformRoleService.delRole(hm);
            resultMap.put("status",Constants.RESULT_SUCCESS);
            resultMap.put("info",Constants.RESULT_SUCCESS_CON);

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status",Constants.RESULT_ERROR);
            resultMap.put("info",Constants.RESULT_ERROR_CON);
        }
        outJsonForMap(resultMap, response);
    }
    /**
     * 分配权限
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/ditributeRoleRules.htm")
    public String ditributeRoleRules(HttpServletRequest request, HttpServletResponse response){
        try {

            HashMap<String,Object> hm = Util.genHmParam(request);
            List<Map<String,Object>> resultList = platformRuleService.getRuleByParentId(hm);
            request.setAttribute("data",resultList);
            request.setAttribute("roleId",hm.get("roleId"));
            request.setAttribute("roleRuleList",platformRoleRuleService.getRoleRuleByRoleId(hm));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "jurisdiction/ditributeRoleRule";
    }
    /**
     * 保存角色权限
     * @param request
     * @param response
     */
    @RequestMapping(value = "/saveRoleRules.htm",method = RequestMethod.POST)
    public void saveRoleRules(HttpServletRequest request, HttpServletResponse response){
        HashMap<String,Object> resultMap = new HashMap<>();
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            int result = platformRoleRuleService.saveRoleRule(hm);
            resultMap.put("status",result);
            resultMap.put("info",Constants.RESULT_SUCCESS_CON);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status",Constants.RESULT_ERROR);
            resultMap.put("info",Constants.RESULT_ERROR_CON);
        }
        outJsonForMap(resultMap, response);
    }
    /**
     * 跳转后台操作人员列表
     * @return
     */
    @RequestMapping(value = "/accountManage.htm",method = RequestMethod.GET)
    public String platUserManage(){
        return "jurisdiction/account/accountManage";
    }
    /**
     * 跳转权限列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/ruleInformation.htm")
    public String ruleInformation(HttpServletRequest request, HttpServletResponse response){

        return "jurisdiction/rule/ruleInformation";
    }

    /**
     * 权限列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getRuleList.htm",method = RequestMethod.GET)
    public void getRulesList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap = platformRuleService.getRuleList(hm);
            outJsonForMap(resultMap, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 跳转修改权限页面
     * @param request
     * @return
     */
    @RequestMapping("/goSaveRule.htm")
    public String goSaveRule(HttpServletRequest request){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);

            request.setAttribute("ruleMap",platformRuleService.getRuleById(hm));
            hm.put("isMenu",Constants.ISNOTMUNE );
            request.setAttribute("menuList",platformRuleService.getRuleByMenu(hm));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "jurisdiction/rule/saveRule";
    }
    /**
     * 修改权限信息
     * @param request
     * @param response
     */
    @RequestMapping("/upRule.htm")
    public void upRule(HttpServletRequest request, HttpServletResponse response){
        HashMap<String,Object> resultMap = new HashMap<>();
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            int result = platformRuleService.saveRule(hm);
            resultMap.put("status",result);
            resultMap.put("info",Constants.RESULT_SUCCESS_CON);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("status",Constants.RESULT_ERROR);
            resultMap.put("info",Constants.RESULT_ERROR_CON);
        }
        outJsonForMap(resultMap, response);
    }
}
