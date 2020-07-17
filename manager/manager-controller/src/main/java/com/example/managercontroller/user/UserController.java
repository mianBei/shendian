package com.example.managercontroller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 跳转用户列表
     * @return
     */
    @RequestMapping(value = "/userInformation.htm",method = RequestMethod.GET)
    public ModelAndView userInformation(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("jurisdiction/userInformation");
        return mv;
    }
}
