package com.example.managercontroller.order;

import com.example.common.superCon.SuperController;
import com.example.common.util.Util;
import com.example.managerDao.order.entity.PlatformBill;
import com.example.managerService.order.IPlatformBillService;
import com.example.managerService.order.IPlatformOrderService;
import com.example.managerService.shop.IShopBillService;
import com.example.managerService.shop.IShopDealLogCashAccountService;
import com.example.managerService.user.IUserBillService;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/order")
public class OrderController extends SuperController {
    @Autowired
    IPlatformBillService billService;
    @Autowired
    IPlatformOrderService orderService;
    @Autowired
    IShopBillService shopBillService;
    @Autowired
    IShopDealLogCashAccountService dealLogCashAccountService;
    @Autowired
    IUserBillService userBillService;
    /**
     * 跳转账单列表
     * @return
     */
    @RequestMapping(value = "/bill.htm",method = RequestMethod.GET)
    public String bill(){
        return "order/bill/bill";
    }

    /**
     * 账单列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getBillList.htm",method = RequestMethod.GET)
    public void getBaseInfoList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap = billService.getBillList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 查询账单详情
     * @return
     */
    @RequestMapping(value = "/selectBill.htm",method = RequestMethod.GET)
    public String selectBill(HttpServletRequest request){
        HashMap<String,Object> hm = Util.genHmParam(request);
        HashMap<String,Object> bill = billService.getBillById(hm);
        request.setAttribute("bill",bill.get("bill"));
        request.setAttribute("shop",bill.get("shop"));
        request.setAttribute("user",bill.get("user"));
        return "order/bill/selectBill";
    }

    /**
     * 跳转订单列表
     * @return
     */
    @RequestMapping(value = "/order.htm",method = RequestMethod.GET)
    public String order(){
        return "order/order/order";
    }

    /**
     * 订单列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getOrderList.htm",method = RequestMethod.GET)
    public void getOrderList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap =orderService.getOrderList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 跳转订单列表
     * @return
     */
    @RequestMapping(value = "/selectOrder.htm",method = RequestMethod.GET)
    public String selectOrder(HttpServletRequest request){
        HashMap<String,Object> hm = Util.genHmParam(request);
        HashMap<String,Object> orderMap = orderService.getOrderById(hm);
        request.setAttribute("order",orderMap);
        return "order/order/selectOrder";
    }

    /**
     * 跳转商家订单
     * @return
     */
    @RequestMapping(value = "shopBill.htm",method = RequestMethod.GET)
    public String shopBill(){
        return "order/shop/bill";
    }

    /**
     * 商家订单列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getShopBillList.htm",method = RequestMethod.GET)
    public void getShopBillList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap =shopBillService.getShopBillList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 跳转商品订单详情
     * @return
     */
    @RequestMapping(value = "/selectShopBill.htm",method = RequestMethod.GET)
    public String selectShopBill(HttpServletRequest request){
        HashMap<String,Object> hm = Util.genHmParam(request);
        HashMap<String,Object> shopMap = shopBillService.getShopBillById(hm);
        request.setAttribute("shop",shopMap);
        return "order/shop/selectShopBill";
    }
    /**
     * 跳转商家交易记录
     * @return
     */
    @RequestMapping(value = "cashLog.htm",method = RequestMethod.GET)
    public String cashLog(){
        return "order/cashLog/cashLog";
    }

    /**
     * 商家交易记录列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getCashLogList.htm",method = RequestMethod.GET)
    public void getCashLogList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap =dealLogCashAccountService.getCashLogList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 跳转商品交易记录详情
     * @return
     */
    @RequestMapping(value = "/selectCashLog.htm",method = RequestMethod.GET)
    public String selectCashLog(HttpServletRequest request){
        HashMap<String,Object> hm = Util.genHmParam(request);
        HashMap<String,Object> cashLogMap = dealLogCashAccountService.getCashLogById(hm);
        request.setAttribute("cashLog",cashLogMap);
        return "order/cashLog/selectCashLog";
    }

    /**
     * 跳转用户列表
     * @return
     */
    @RequestMapping(value = "/userBill.htm",method = RequestMethod.GET)
    public String userBill(){
        return "order/user/bill";
    }

    /**
     * 用户账单列表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getUserBillList.htm",method = RequestMethod.GET)
    public void getUserBillList(HttpServletRequest request, HttpServletResponse response){
        try {
            HashMap<String,Object> hm = Util.genHmParam(request);
            HashMap<String,Object> resultMap =userBillService.getUserBillList(hm);
            outJsonForMap(resultMap,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 跳转用户账单详情
     * @return
     */
    @RequestMapping(value = "/selectUser.htm",method = RequestMethod.GET)
    public String selectUser(HttpServletRequest request){
        HashMap<String,Object> hm = Util.genHmParam(request);
        request.setAttribute("bill",userBillService.getUserBillById(hm));
        return "order/user/selectUserBill";
    }
}
