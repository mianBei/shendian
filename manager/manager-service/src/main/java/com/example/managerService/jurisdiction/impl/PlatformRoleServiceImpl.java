package com.example.managerService.jurisdiction.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.StringUtils;
import com.example.managerDao.jurisdiction.entity.PlatformRole;
import com.example.managerDao.jurisdiction.entity.PlatformRoleRule;
import com.example.managerDao.jurisdiction.mapper.PlatformRoleMapper;
import com.example.managerDao.jurisdiction.mapper.PlatformRoleRuleMapper;
import com.example.managerService.jurisdiction.IPlatformRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Service
public class PlatformRoleServiceImpl extends ServiceImpl<PlatformRoleMapper, PlatformRole> implements IPlatformRoleService {
    @Autowired
    PlatformRoleMapper platformRoleMapper;
    @Autowired
    PlatformRoleRuleMapper platformRoleRuleMapper;

    /**
     * 根据id获取角色信息
     * @param roleId
     * @return
     */
    @Override
    public PlatformRole getRoleById(int roleId) {
        PlatformRole platformRole = platformRoleMapper.selectOne(new LambdaQueryWrapper<PlatformRole>()
                .eq(PlatformRole::getRoleId,roleId)
        );
        return platformRole;
    }

    /**
     * 查询角色分页
     * @return
     */
    @Override
    public HashMap<String,Object> getRoleList(HashMap<String,Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String roleName = map.get("roleName").toString();
        IPage<Map<String,Object>> platformRoleList = platformRoleMapper.selectMapsPage(pageN,new LambdaQueryWrapper<PlatformRole>()
                .like(StringUtils.isNotBlank(roleName),PlatformRole::getRoleName,roleName)
        );
        List<Map<String,Object>> roleRecordList = platformRoleList.getRecords();
        for (Map<String,Object> roleRecordMap: roleRecordList) {
            roleRecordMap.put("index", ++start);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",roleRecordList);
        resultMap.put("totalCount",platformRoleList.getTotal());
        return resultMap;
    }

    /**
     * 添加或修改角色
     * @param map
     * @return
     */
    @Override
    public int saveRole(HashMap<String, Object> map) {
        int id = Integer.parseInt(map.get("id").toString());
        int result = 0;
        //如果id不等于说明是修改，否则是添加
        PlatformRole role = new PlatformRole();
        role.setNote(map.get("note").toString());
        role.setRoleName(map.get("roleName").toString());
        if (id!=0){
            role.setRoleId(Integer.parseInt(map.get("id").toString()));
            result = platformRoleMapper.updateById(role);
        }else{
            result = platformRoleMapper.insert(role);
        }
        return result;
    }

    /**
     * 删除角色
     * @param map
     * @return
     */
    @Override
    public void delRole(HashMap<String, Object> map) {
        int roleId = Integer.parseInt(map.get("roleId").toString());
        platformRoleMapper.deleteById(roleId);
        platformRoleRuleMapper.delete(new LambdaQueryWrapper<PlatformRoleRule>()
                .eq(PlatformRoleRule::getRoleId,roleId)
        );
    }
}
