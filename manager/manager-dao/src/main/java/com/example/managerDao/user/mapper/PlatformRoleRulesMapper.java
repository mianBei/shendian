package com.example.managerDao.user.mapper;

import com.example.managerDao.user.entity.PlatformRoleRules;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
public interface PlatformRoleRulesMapper extends BaseMapper<PlatformRoleRules> {
    /**
     * 查询关联表信息
     * @param map
     * @return
     */
    @Select("SELECT ru.functionName,ru.url,ru.name" +
            "FROM platform_role_rule r" +
            "INNER JOIN platform_rule ru ON ru.ruleId=r.ruleId" +
            "WHERE r.parentId=#{parentId} AND roleId=#{roleId} AND ru.isMenu=#{isMenu}")
    List<HashMap<String,String>> getRoleRulesList(HashMap<String,Object> map);

    /**
     *  根据roleId查询权限表
     * @param map
     * @return
     */
    @Select("select a.* " +
            "from platform_rule a " +
            "where a.isMenu=1 and a.parentId=#{parentId} and a.ruleId in " +
            "(select ruleId from platform_role_rule where roleId = #{roleId} )order by a.orderValue")
    List<HashMap<String,Object>> getRoleMenuRules(HashMap<String,Object> map);
}
