package com.example.managerService.jurisdiction;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.PlatformRoleRule;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
public interface IPlatformRoleRuleService extends IService<PlatformRoleRule> {

    /**
     *  根据roleId查询权限表
     * @param roleId
     * @return
     */
    List<HashMap<String,Object>> getRoleMenuRules(int roleId);

    /**
     * 当前用户所拥有的权限
     * @param map
     * @return
     */
    List<Integer> getRoleRuleByRoleId(HashMap<String,Object> map);

    /**
     * 修改用户权限
     * @param map
     * @return
     */
    int saveRoleRule(HashMap<String,Object> map);
}
