package com.example.managercontroller.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.bean.Constants;
import com.example.managerDao.jurisdiction.entity.PlatformAccount;
import com.example.managerDao.jurisdiction.mapper.PlatformAccountMapper;
import com.example.managerDao.jurisdiction.mapper.PlatformRoleRuleMapper;
import com.example.managerDao.shop.entity.ShopAccount;
import com.example.managerDao.user.mapper.*;
import com.example.managerService.shop.IShopAccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Realm extends AuthorizingRealm {
    @Autowired
    PlatformAccountMapper platformAccountMapper;
    @Autowired
    PlatformRoleRuleMapper platformRoleRuleMapper;
    @Autowired
    IShopAccountService accountService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userCode = (String) principals.getPrimaryPrincipal();
        PlatformAccount platformAccount = platformAccountMapper.selectOne(new LambdaQueryWrapper<PlatformAccount>()
            .eq(PlatformAccount::getPlatformAccountCode,userCode)
        );
        HashMap<String,Object> userMap = new HashMap<>();
        if (platformAccount==null){
            ShopAccount shopAccount = accountService.getAccountByCode(userCode);
            userMap.put("roleId",shopAccount.getRoleId());
        }else{
            userMap.put("roleId",platformAccount.getRoleId());
        }
        // 根据身份信息获取权限信息
        List<String> permissions = new ArrayList<>();
        userMap.put("parentId",request.getParameter("r_id"));
        userMap.put("isMenu",Constants.ISMUNE);
        List<HashMap<String,String>> roleRuleList = platformRoleRuleMapper.getRoleRulesList(userMap);
        HashMap<String,Object> resultMap = new HashMap<>();
        for (HashMap<String,String> permissionMap:roleRuleList) {
            String function_name = permissionMap.get("functionName");
            permissions.add("user:"+function_name);// 用户的创建
            distinguishData(permissionMap,resultMap);
        }
        // 查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissions);
        request.setAttribute("rule",roleRuleList);
        request.setAttribute("permissionMap",resultMap);
        return simpleAuthorizationInfo;
    }
    /**
     * 区分数据库中的链接
     */
    public HashMap<String,Object> distinguishData(HashMap<String,String> map,HashMap<String,Object> resultMap){

        String function_name = map.get("functionName");
        String name = map.get("name");
        String url = map.get("url");
        resultMap.put(function_name+Constants.NAME,name);
        resultMap.put(function_name+Constants.URL,url);
        return resultMap;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        String username = (String)token.getPrincipal();
        PlatformAccount platformAccount = platformAccountMapper.selectOne(new LambdaQueryWrapper<PlatformAccount>()
            .eq(PlatformAccount::getPlatformAccountCode,username)
        );
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo();
        if(platformAccount == null){
            //查询商家用户
            ShopAccount shopAccount = accountService.getAccountByCode(username);
            if (shopAccount==null){
                throw new UnknownAccountException("用户不存在");
            }
            authenticationInfo = new SimpleAuthenticationInfo(
                    username, //用户名
                    shopAccount.getShopAccountPassword(), //密码
                    ByteSource.Util.bytes(username),//salt=username+salt
                    getName()  //realm name
            );
        }else{
            authenticationInfo = new SimpleAuthenticationInfo(
                    username, //用户名
                    platformAccount.getPlatformAccountPassword(), //密码
                    ByteSource.Util.bytes(username),//salt=username+salt
                    getName()  //realm name
            );
        }
        return authenticationInfo;
    }
}
