package com.example.managercontroller.shop;

import com.example.common.superCon.SuperController;
import com.example.common.util.Util;
import com.example.managerService.shop.IShopAccountService;
import com.example.managerService.shop.IShopBalanceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/shop")
public class shopController extends SuperController {

    @Autowired
    IShopAccountService accountService;
    @Autowired
    IShopBalanceDataService balanceDataService;
    /**
     * 跳转商户信息
     * @return
     */
    @RequestMapping(value = "/account.htm" ,method = RequestMethod.GET)
    public String basicInfo(){
        return "shop/account/account";
    }

    /**
     * 商户信息列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getAccountList.htm",method = RequestMethod.GET)
    public void getAccountList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap = accountService.getAccountList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 商家用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "getAccountUser.htm",method = RequestMethod.GET)
    public String getAccountUser(HttpServletRequest request){
        HashMap<String,Object> hm = Util.genHmParam(request);
        request.setAttribute("user",accountService.getAccountUserById(hm));
        return "shop/account/selectUser";
    }

    /**
     * 查询商户余额
     * @param request
     * @return
     */
    @RequestMapping(value = "getBalanceById.htm",method = RequestMethod.GET)
    public String getBalanceById(HttpServletRequest request){
        HashMap<String,Object> hm = Util.genHmParam(request);
        request.setAttribute("balance",balanceDataService.getBalanceById(hm));
        return "shop/account/selectBalance";
    }
}
