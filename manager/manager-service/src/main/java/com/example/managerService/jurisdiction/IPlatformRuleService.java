package com.example.managerService.jurisdiction;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.PlatformRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
public interface IPlatformRuleService extends IService<PlatformRule> {
    /**
     * 获取菜单列表
     * @param map
     * @return
     */
    List<Map<String,Object>> getRuleByParentId(HashMap<String,Object> map);

    /**
     * 菜单列表分页
     * @param map
     * @return
     */
    HashMap<String,Object> getRuleList(HashMap<String,Object> map);

    /**
     * 根据id获取内容
     * @param map
     * @return
     */
    PlatformRule getRuleById(HashMap<String,Object> map);

    /**
     * 查询菜单
     * @param map
     * @return
     */
    List<PlatformRule> getRuleByMenu(HashMap<String,Object> map);

    /**
     * 修改菜单
     * @param map
     * @return
     */
    int saveRule(HashMap<String,Object> map);
}
