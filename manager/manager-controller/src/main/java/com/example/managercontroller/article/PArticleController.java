package com.example.managercontroller.article;


import com.example.common.bean.Constants;
import com.example.common.superCon.SuperController;
import com.example.common.util.Util;
import com.example.managerService.article.IPArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-05-07
 */
@RestController
@RequestMapping("/article/p-article")
@Api(description = "文章API")
public class PArticleController extends SuperController {
    @Autowired
    IPArticleService articleService;
    @ApiOperation(value = "创建索引",notes = "创建文章的索引文件")
    @RequestMapping(value = "/createLucene.htm",method = RequestMethod.GET)
    public HashMap<String, Object> createLucene(HttpServletRequest request){
        HashMap<String,Object> resultMap = new HashMap<>();
        try {
            String result = articleService.createLucene();
            resultMap.put("status", Constants.RESULT_SUCCESS);
            resultMap.put("info", Constants.RESULT_SUCCESS_CON);
            resultMap.put("data", result);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("status", Constants.RESULT_ERROR);
            resultMap.put("info",Constants.RESULT_ERROR_CON);
        }
        return resultMap;
    }
}
