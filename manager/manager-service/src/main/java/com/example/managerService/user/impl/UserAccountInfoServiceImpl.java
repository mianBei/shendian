package com.example.managerService.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.StringUtils;
import com.example.common.util.Util;
import com.example.managerDao.user.entity.UserAccountInfo;
import com.example.managerDao.user.mapper.UserAccountInfoMapper;
import com.example.managerService.user.IUserAccountInfoService;
import com.example.managerService.user.IUserBalanceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-22
 */
@Service
public class UserAccountInfoServiceImpl extends ServiceImpl<UserAccountInfoMapper, UserAccountInfo> implements IUserAccountInfoService {
    @Value("${pic.url_prefix}")
    private String url_prefix;
    @Autowired
    UserAccountInfoMapper accountInfoMapper;
    @Autowired
    IUserBalanceDataService balanceDataService;
    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    @Override
    public UserAccountInfo getUserById(String userId) {
        return accountInfoMapper.selectOne(new LambdaQueryWrapper<UserAccountInfo>()
                .eq(UserAccountInfo::getUserId,userId)
        );
    }

    /**
     * 用户列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getUserAccountList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String userNickname = map.get("userNickname").toString();
        IPage<Map<String,Object>> messagePostList = accountInfoMapper.selectMapsPage(pageN,new LambdaQueryWrapper<UserAccountInfo>()
                .like(org.apache.commons.lang.StringUtils.isNotBlank(userNickname),UserAccountInfo::getUserNickname,userNickname)
                .orderByDesc(UserAccountInfo::getAddTime)
        );
        List<Map<String,Object>> recordList = messagePostList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",messagePostList.getTotal());
        return resultMap;
    }

    /**
     * 根据id查询返回map类型
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getMapUserById(HashMap<String, Object> map) {
        Object userInfoId = map.get("userInfoId");
        HashMap<String,Object> resultMap = new HashMap<>();
        UserAccountInfo accountInfo = new UserAccountInfo();
        if (userInfoId!=null){
            accountInfo = accountInfoMapper.selectById(userInfoId.toString());
            String img = accountInfo.getUserHeadImage();
            String name = img.substring(img.lastIndexOf("/",img.lastIndexOf("/")-1));
            resultMap.put("picName",name);
            List<String> pics = Util.getWholePictureArrayPath(img,"",",",url_prefix);
            accountInfo.setUserHeadImage(pics.get(0));
        }
        resultMap.put("userAccount",accountInfo);
        //查询用户余额信息
        resultMap.put("balance",balanceDataService.getUserBalanceById(accountInfo.getUserId()));
        return resultMap;
    }
}
