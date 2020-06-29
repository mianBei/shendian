package com.example.managercontroller.user;


import com.example.managerDao.user.entity.AccountInfo;
import com.example.managerService.user.IAccountInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-06-29
 */
@RestController
@RequestMapping("/user/account-info")
@Api(description = "用户API")
public class AccountInfoController {
    @Autowired
    IAccountInfoService accountInfoService;
    @ApiOperation(value = "测试",notes = "获取所有信息")
    @RequestMapping(value = "/test.htm",method = RequestMethod.GET)
    public List<AccountInfo> test(){
        List<AccountInfo> list = accountInfoService.getAccountList();
        return list;
    }
}
