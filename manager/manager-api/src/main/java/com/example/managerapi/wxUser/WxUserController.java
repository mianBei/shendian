package com.example.managerapi.wxUser;

import com.example.common.bean.Constants;
import com.example.common.util.Util;
import com.example.managerService.wxUser.IPlatformAppConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-07-05
 */
@RestController
@RequestMapping("/wxUser")
@Api(description = "微信操作API接口")
public class WxUserController {
    @Autowired
    IPlatformAppConfigService platformAppConfigService;

    @ApiOperation(value = "微信授权",notes = "微信用户授权")
    @RequestMapping(value = "/wxAuthorization.htm",method = RequestMethod.GET)
    public HashMap<String, Object> wxAuthorization(HttpServletRequest request){
        HashMap<String,Object> resultMap = new HashMap<>();
        HashMap<String,Object> map = Util.genHmParam(request);
        HashMap<String,Object> authorizationMap = platformAppConfigService.wxAuthorization(map);
        resultMap.put("list",authorizationMap);
        resultMap.put("code", Constants.RESULT_SUCCESS);
        resultMap.put("msg", Constants.RESULT_SUCCESS_CON);
        return resultMap;
    }
}
