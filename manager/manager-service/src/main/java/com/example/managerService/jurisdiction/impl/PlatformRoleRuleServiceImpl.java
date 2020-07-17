package com.example.managerService.jurisdiction.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.bean.Constants;
import com.example.managerDao.user.entity.PlatformRoleRule;
import com.example.managerDao.user.mapper.PlatformRoleRuleMapper;
import com.example.managerService.jurisdiction.IPlatformRoleRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class PlatformRoleRuleServiceImpl extends ServiceImpl<PlatformRoleRuleMapper, PlatformRoleRule> implements IPlatformRoleRuleService {
    @Autowired
    PlatformRoleRuleMapper platformRoleRuleMapper;

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
        List<HashMap<String,Object>> ruleList=platformRoleRuleMapper.getRoleMenuRules(pm);
        if(ruleList!=null){
            for(HashMap<String,Object> map:ruleList){
                fillSubRules(map,roleId);
            }
        }
        return ruleList;
    }

    /**
     * 当前用户的权限
     * @param map
     * @return
     */
    @Override
    public List<Integer> getRoleRuleByRoleId(HashMap<String, Object> map) {
        List<PlatformRoleRule> platformRoleRuleList = platformRoleRuleMapper.selectList(new LambdaQueryWrapper<PlatformRoleRule>()
                .eq(PlatformRoleRule::getRoleId,map.get("roleId"))
        );
        List<Integer> resultList = new ArrayList<>();
        for (PlatformRoleRule roleRu:platformRoleRuleList) {
            resultList.add(roleRu.getRuleId());
        }
        return resultList;
    }

    /**
     * 修改用户权限
     * @param map
     * @return
     */
    @Override
    public int saveRoleRule(HashMap<String, Object> map) {
        int result=0;
        platformRoleRuleMapper.delete(new LambdaQueryWrapper<PlatformRoleRule>()
                .eq(PlatformRoleRule::getRoleId,map.get("roleId"))
        );
        PlatformRoleRule platformRoleRule = new PlatformRoleRule();
        String[] checkList = map.get("checkValue").toString().split(",");
        platformRoleRule.setRoleId(Integer.parseInt(map.get("roleId").toString()));
        for (String ruleId:checkList) {
            if (ruleId.contains("+")){
                platformRoleRule.setParentId(Integer.parseInt(ruleId.substring(ruleId.substring(0, ruleId.indexOf("+")).length()+1, ruleId.length())));
                platformRoleRule.setRuleId(Integer.parseInt(ruleId.substring(0, ruleId.indexOf("+"))));
            }else{
                platformRoleRule.setRuleId(Integer.parseInt(ruleId));
            }
            result = platformRoleRuleMapper.insert(platformRoleRule);
        }
        return result;
    }

    private HashMap<String, Object> fillSubRules(HashMap<String, Object> map,int roleId) {
        HashMap<String,Object> pm = new HashMap<String,Object>();
        pm.put("roleId", roleId);
        pm.put("parentId", map.get("ruleId"));
        List<HashMap<String,Object>> list =platformRoleRuleMapper.getRoleMenuRules(pm);
        if(list!=null){
            for(HashMap<String,Object> subMap:list){
                if (!subMap.containsKey("parameter")){
                    subMap.put("parameter",null);
                }
                fillSubRules(subMap,roleId);
            }
        }
        map.put("children", list);
        return map;
    }
}
