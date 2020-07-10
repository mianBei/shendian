package com.example.managerService.platformUser.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.bean.Constants;
import com.example.managerDao.user.entity.PlatformRoleRules;
import com.example.managerDao.user.mapper.PlatformRoleRulesMapper;
import com.example.managerService.platformUser.IPlatformRoleRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Service
public class PlatformRoleRulesServiceImpl extends ServiceImpl<PlatformRoleRulesMapper, PlatformRoleRules> implements IPlatformRoleRulesService {
    @Autowired
    PlatformRoleRulesMapper platformRoleRulesMapper;

    /**
     * 根据roleId查询权限菜单表
     * @param roleId
     * @return
     */
    @Override
    public List<HashMap<String, Object>> getRoleMenuRules(int roleId) {
        HashMap<String,Object> pm = new HashMap<String,Object>();
        pm.put("roleId", roleId);
        pm.put("parentId", Constants.PARENTID);
        List<HashMap<String,Object>> ruleList=platformRoleRulesMapper.getRoleMenuRules(pm);
        if(ruleList!=null){
            for(HashMap<String,Object> map:ruleList){
                fillSubRules(map,roleId);
            }
        }
        return ruleList;
    }
    private HashMap<String, Object> fillSubRules(HashMap<String, Object> map,int roleId) {
        HashMap<String,Object> pm = new HashMap<String,Object>();
        pm.put("roleId", roleId);
        pm.put("parentId", map.get("ruleId"));
        List<HashMap<String,Object>> list =platformRoleRulesMapper.getRoleMenuRules(pm);
        if(list!=null){
            for(HashMap<String,Object> subMap:list){
                fillSubRules(subMap,roleId);
            }
        }
        map.put("children", list);
        return map;
    }
}
