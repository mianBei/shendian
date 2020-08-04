package com.example.managercontroller.shop;

import com.example.common.bean.Constants;
import com.example.common.superCon.SuperController;
import com.example.common.util.Util;
import com.example.managerDao.shop.entity.ShopAccount;
import com.example.managerDao.shop.entity.ShopCommodity;
import com.example.managerService.shop.*;
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
    @Autowired
    IShopBasicInfoService basicInfoService;
    @Autowired
    IShopCommodityService commodityService;
    @Autowired
    IShopHelpGratisEventService helpGratisEventService;
    @Autowired
    IShopMessageSystemService messageSystemService;
    /**
     * 跳转商户信息
     * @return
     */
    @RequestMapping(value = "/account.htm" ,method = RequestMethod.GET)
    public String account(){
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
    @RequestMapping(value = "selectUser.htm",method = RequestMethod.GET)
    public String selectUser(HttpServletRequest request){
        HashMap<String,Object> hm = Util.genHmParam(request);
        request.setAttribute("user",accountService.getAccountUserById(hm));
        return "shop/account/selectUser";
    }

    /**
     * 查询商户余额
     * @param request
     * @return
     */
    @RequestMapping(value = "selectBalance.htm",method = RequestMethod.GET)
    public String selectBalance(HttpServletRequest request){
        HashMap<String,Object> hm = Util.genHmParam(request);
        request.setAttribute("balance",balanceDataService.getBalanceById(hm));
        return "shop/account/selectBalance";
    }

    /**
     * 跳转商户信息
     * @return
     */
    @RequestMapping(value = "basicInfo.htm",method = RequestMethod.GET)
    public String basicInfo(){
        return "shop/basicInfo/basicInfo";
    }

    /**
     * 商家信息列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getBasicInfoList.htm",method = RequestMethod.GET)
    public void getBasicInfoList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap = basicInfoService.getBasicInfoList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 跳转修改页面
     * @param request
     * @return
     */
    @RequestMapping(value = "selectBasicInfo.htm",method = RequestMethod.GET)
    public String selectBasicInfo(HttpServletRequest request){
        HashMap<String,Object> hm = Util.genHmParam(request);
        request.setAttribute("basicInfo",basicInfoService.getBasicInfoById(hm));
        return "shop/basicInfo/selectBasicInfo";
    }

    /**
     * 跳转商品管理
     * @return
     */
    @RequestMapping(value = "commodity.htm",method = RequestMethod.GET)
    public String commodity(){
        return "shop/commodity/commodity";
    }

    /**
     * 商品列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getCommodityList.htm",method = RequestMethod.GET)
    public void getCommodityList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap = commodityService.getCommodityList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 跳转商品修改页面
     * @param request
     * @return
     */
    @RequestMapping(value = "goSaveCommodity.htm",method = RequestMethod.GET)
    public String goSaveCommodity(HttpServletRequest request){
        HashMap<String,Object> hm = Util.genHmParam(request);
        request.setAttribute("commodity",commodityService.getCommodityById(hm));
        return "shop/commodity/saveCommodity";
    }

    /**
     * 修改商品
     * @param request
     * @param response
     */
    @RequestMapping(value = "/saveCommodity.htm",method = RequestMethod.POST)
    public void saveCommodity(HttpServletRequest request, HttpServletResponse response){
        HashMap<String,Object> resultMap = new HashMap<>();
        try {
            HashMap<String,ShopAccount> userBean=(HashMap)request.getSession().getAttribute("userSession");
            ShopAccount accountInfo = userBean.get("accountInfo");
            HashMap<String,Object> hm = Util.genHmParam(request);
            hm.put("platformId",accountInfo.getPlatformId());
            hm.put("bindShopId",accountInfo.getShopId());
            hm.put("uploaderShopAccountId",accountInfo.getShopAccountId());
            hm.put("uploaderUserId",accountInfo.getBindUserId());
            hm.put("uploaderShopAuth",accountInfo.getShopAccountAuth());
            int result = commodityService.saveCommodity(hm);
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
     * 跳转助力免单
     * @return
     */
    @RequestMapping(value = "helpGratis.htm",method = RequestMethod.GET)
    public String helpGratis(){
        return "shop/helpGratis/helpGratis";
    }

    /**
     * 助力免单列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getHelpGratisList.htm",method = RequestMethod.GET)
    public void getHelpGratisList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap = helpGratisEventService.getHelpGratisList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 跳转修改助力免单
     * @param request
     * @return
     */
    @RequestMapping(value = "goSaveHelpGratis.htm",method = RequestMethod.GET)
    public String goSaveHelpGratis(HttpServletRequest request){
        HashMap<String,Object> hm = Util.genHmParam(request);
        request.setAttribute("helpGratis",helpGratisEventService.getHelpGratisEventById(hm));
        return "shop/helpGratis/saveHelpGratis";
    }

    /**
     * 修改助力商品
     * @param request
     * @param response
     */
    @RequestMapping(value = "/saveHelpGratis.htm",method = RequestMethod.POST)
    public void saveHelpGratis(HttpServletRequest request, HttpServletResponse response){
        HashMap<String,Object> resultMap = new HashMap<>();
        try {
            HashMap<String,ShopAccount> userBean=(HashMap)request.getSession().getAttribute("userSession");
            ShopAccount accountInfo = userBean.get("accountInfo");
            HashMap<String,Object> hm = Util.genHmParam(request);
            hm.put("platformId",accountInfo.getPlatformId());
            hm.put("bindShopId",accountInfo.getShopId());
            int result = helpGratisEventService.saveHelpGratis(hm);
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
     * 跳转商家消息
     * @return
     */
    @RequestMapping(value = "messageSystem.htm",method = RequestMethod.GET)
    public String messageSystem(){
        return "shop/messageSystem/messageSystem";
    }

    /**
     * 商家消息列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getMessageSystemList.htm",method = RequestMethod.GET)
    public void getMessageSystemList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,ShopAccount> userBean=(HashMap)request.getSession().getAttribute("userSession");
            ShopAccount accountInfo = userBean.get("accountInfo");
            hm.put("bindShopId",accountInfo.getShopId());
            HashMap<String,Object> resultMap = messageSystemService.getMessageSystemList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
