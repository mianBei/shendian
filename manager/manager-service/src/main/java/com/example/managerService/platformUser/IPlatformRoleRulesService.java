package com.example.managerService.platformUser;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.PlatformRoleRules;
import org.apache.ibatis.annotations.Select;

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
public interface IPlatformRoleRulesService extends IService<PlatformRoleRules> {

    /**
     *  根据roleId查询权限表
     * @param roleId
     * @return
     */
    List<HashMap<String,Object>> getRoleMenuRules(int roleId);
}
