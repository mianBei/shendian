package com.example.managerapi.controller;

import com.example.managerDao.user.entity.AccountInfo;
import com.example.managerService.user.IAccountInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class testController {
    @RequestMapping(value = "/test.htm",method = RequestMethod.GET)
    public String test(){

        return "test";
    }
}
