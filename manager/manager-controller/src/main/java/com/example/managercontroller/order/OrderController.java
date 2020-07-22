package com.example.managercontroller.order;

import com.example.common.superCon.SuperController;
import com.example.common.util.Util;
import com.example.managerDao.order.entity.PlatformBill;
import com.example.managerService.order.IPlatformBillService;
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
}
