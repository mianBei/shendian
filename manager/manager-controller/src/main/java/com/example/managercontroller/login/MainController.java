package com.example.managercontroller.login;

import com.example.common.util.StringUtils;
import com.example.managerDao.jurisdiction.entity.PlatformAccount;
import com.example.managerDao.jurisdiction.entity.PlatformRole;
import com.example.managerService.jurisdiction.IPlatformAccountService;
import com.example.managerService.jurisdiction.IPlatformRoleRuleService;
import com.example.managerService.jurisdiction.IPlatformRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/")
public class MainController {
    @Autowired
    IPlatformRoleService platformRoleService;
    @Autowired
    IPlatformAccountService platformAccountService;
    @Autowired
    IPlatformRoleRuleService platformRoleRuleService;
    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping(value = "/login.htm",method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("base/login");
        return mv;
    }
    /**
     * 跳转首页
     * @return
     */
    @RequestMapping(value = "/shouye.htm",method = RequestMethod.GET)
    public ModelAndView shouye(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("base/shouye");
        return mv;
    }

    /**
     * 主页面菜单列表查询
     * @return
     */
    @RequestMapping(value = "/main.htm",method = RequestMethod.GET)
    public ModelAndView main(HttpServletRequest request, Model model){
        ModelAndView mv = new ModelAndView();
        HashMap<String,PlatformAccount> userBean=(HashMap)request.getSession().getAttribute("userSession");
        PlatformAccount accountInfo = userBean.get("accountInfo");
        if(accountInfo==null){
            mv.setViewName("base/login");
            return mv;
        }
        int roleId = accountInfo.getRoleId();
        List<HashMap<String,Object>>  ruleList = platformRoleRuleService.getRoleMenuRules(roleId);
        model.addAttribute("ruleList", ruleList);
        model.addAttribute("userSession", userBean);
        mv.setViewName("base/main");
        return mv;
    }
    /**
     * 登录验证
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/doLogin.htm",method = RequestMethod.POST)
    public HashMap<String, Object> doLogin(HttpServletRequest request, HashMap<String, Object> map){
        String username= StringUtils.catchNull(request.getParameter("code"));
        String password=StringUtils.catchNull(request.getParameter("password"));
        HashMap<String,Object> resultMap=new HashMap<String,Object>();
        if (username.isEmpty() || password.isEmpty()) {
            resultMap.put("status",0);
            resultMap.put("error_info", "用户名或密码不能为空!");
        } else {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                subject.login(token);
                PlatformAccount platformAccount = platformAccountService.getAccountByCode(username);
                PlatformRole platformRole=new PlatformRole();
                int roleId = platformAccount.getRoleId();
                if(roleId!=0){
                    platformRole=platformRoleService.getRoleById(roleId);
                }
                HashMap<String,Object> userMap = new HashMap<>();
                userMap.put("accountInfo", platformAccount );
                userMap.put("platformRole", platformRole);
                request.getSession().setAttribute("userSession", userMap);
                resultMap.put("status",1);
                resultMap.put("info", "登录成功!");
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put("status",0);
                resultMap.put("info", "用户名或密码错误!");
            }

        }
        return resultMap;
    }
    /**
     * 退出登录
     */
    @RequestMapping("/loginOut.htm")
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute("userSession");
        SecurityUtils.getSubject().logout();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("base/login");
        return mv;
    }
}
