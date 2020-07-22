package com.example.managerService.jurisdiction.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.StringUtils;
import com.example.managerDao.jurisdiction.entity.PlatformRule;
import com.example.managerDao.jurisdiction.mapper.PlatformRuleMapper;
import com.example.managerService.jurisdiction.IPlatformRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Service
public class PlatformRuleServiceImpl extends ServiceImpl<PlatformRuleMapper, PlatformRule> implements IPlatformRuleService {
    @Autowired
    PlatformRuleMapper platformRuleMapper;
    /**
     * 获取菜单列表
     * @param map
     * @return
     */
    @Override
    public List<Map<String, Object>> getRuleByParentId(HashMap<String, Object> map) {
        List<Map<String,Object>> platformRuleList = platformRuleMapper.selectMaps(new LambdaQueryWrapper<PlatformRule>()
                .eq(PlatformRule::getParentId,map.get("parentId"))
        );
        for (Map<String,Object> ruleMap:platformRuleList) {
            List<Map<String,Object>> platformRuleList2 = platformRuleMapper.selectMaps(new LambdaQueryWrapper<PlatformRule>()
                    .eq(PlatformRule::getParentId,ruleMap.get("ruleId"))
            );
            ruleMap.put("roleTwoList",platformRuleList2);
            for (Map<String,Object> ruleMap2:platformRuleList2) {
                List<Map<String,Object>> platformRuleList3 = platformRuleMapper.selectMaps(new LambdaQueryWrapper<PlatformRule>()
                        .eq(PlatformRule::getParentId,ruleMap2.get("ruleId"))
                        .eq(PlatformRule::getIsMenu,0)
                );

                    ruleMap2.put("roleThreeList",platformRuleList3);

            }
        }
        platformRuleList.remove(0);
        return platformRuleList;
    }

    /**
     * 菜单列表分页
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getRuleList(HashMap<String, Object> map) {

        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String name = map.get("name").toString();
        IPage<Map<String,Object>> platformRuleList = platformRuleMapper.selectMapsPage(pageN,new LambdaQueryWrapper<PlatformRule>()
                .like(StringUtils.isNotBlank(name),PlatformRule::getName,name)
        );
        List<Map<String,Object>> recordList = platformRuleList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",platformRuleList.getTotal());
        return resultMap;
    }

    /**
     * 根据id获取内容
     * @param map
     * @return
     */
    @Override
    public PlatformRule getRuleById(HashMap<String, Object> map) {
        Object ruleId = map.get("ruleId");
        PlatformRule platformRule = new PlatformRule();
        if (!"0".equals(ruleId)){
            platformRule = platformRuleMapper.selectOne(new LambdaQueryWrapper<PlatformRule>()
                    .eq(PlatformRule::getRuleId,map.get("ruleId"))
            );
        }
        return platformRule;
    }

    /**
     * 获取用户菜单
     * @param map
     * @return
     */
    @Override
    public List<PlatformRule> getRuleByMenu(HashMap<String, Object> map) {
        return platformRuleMapper.selectList(new LambdaQueryWrapper<PlatformRule>()
                .eq(PlatformRule::getIsMenu,map.get("isMenu"))
        );
    }

    /**
     * 修改菜单
     * @param map
     * @return
     */
    @Override
    public int saveRule(HashMap<String, Object> map) {
        int result=0;
        PlatformRule platformRule = new PlatformRule();
        platformRule.setName((String)map.get("name"));
        platformRule.setUrl((String)map.get("url"));
        platformRule.setParentId(Integer.parseInt(map.get("parentId").toString()));
        platformRule.setIsMenu(Integer.parseInt(map.get("isMenu").toString()));
        platformRule.setOrderValue(Integer.parseInt(map.get("orderValue").toString()));
        platformRule.setClassName((String)map.get("className"));
        platformRule.setParameter((String)map.get("parameter"));
        platformRule.setFunctionName((String)map.get("functionName"));
        platformRule.setCreateTime(LocalDateTime.now());
        Object ruleId = map.get("ruleId");
        if (ruleId==null||"".equals(ruleId)){
            result = platformRuleMapper.insert(platformRule);
        }else{
            platformRule.setRuleId(Integer.parseInt(ruleId.toString()));
            result = platformRuleMapper.updateById(platformRule);
        }
        return result;
    }
}
