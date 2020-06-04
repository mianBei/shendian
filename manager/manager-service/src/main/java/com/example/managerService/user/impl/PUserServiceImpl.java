package com.example.managerService.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.bean.Constants;
import com.example.common.util.DateUtils;
import com.example.managerDao.user.entity.PUser;
import com.example.managerDao.user.mapper.PUserMapper;
import com.example.managerService.user.IPUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-05-07
 */
@Service
public class PUserServiceImpl extends ServiceImpl<PUserMapper, PUser> implements IPUserService {
    @Autowired
    PUserMapper userMapper;

    /**
     * 添加用户
     * @param hm
     * @return
     */
    @Override
    public int insertUser(HashMap<String, Object> hm) {
        PUser user = new PUser();
        user.setOpenid(hm.get("openId").toString());
        user.setHeadPortrait(hm.get("avatarUrl").toString());
        user.setSex((int)hm.get("gender"));
        user.setName(hm.get("nickName").toString());
        user.setCreateTime(LocalDateTime.now());
        user.setReputation(Constants.REPUTATION);
        return userMapper.insert(user);
    }

    /**
     * 修改用户信息
     * @param hm
     * @return
     */
    @Override
    public int updateUser(HashMap<String, Object> hm) {
        PUser user = new PUser();
        user.setId(hm.get("id").toString());
        user.setPhone(hm.get("phone").toString());
        return userMapper.updateById(user);
    }
}
