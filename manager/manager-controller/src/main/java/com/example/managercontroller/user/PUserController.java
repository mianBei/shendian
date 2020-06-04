package com.example.managercontroller.user;

import com.example.common.superCon.SuperController;
import com.example.managerDao.user.entity.PUser;
import com.example.managerDao.user.mapper.PUserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-05-07
 */
@RestController
@RequestMapping("/user/p-user")
@Api(description = "用户API")
public class PUserController extends SuperController {
    /*@Autowired
    PUserMapper pUserMapper;
    @ApiOperation(value = "测试",notes = "获取所有用户信息")
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public List<PUser> test(){
        List<PUser> list = pUserMapper.selectList(null);
        return list;
    }*/
}
