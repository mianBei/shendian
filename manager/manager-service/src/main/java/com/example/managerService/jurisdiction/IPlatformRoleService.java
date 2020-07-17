package com.example.managerService.jurisdiction;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.PlatformRole;

import java.util.HashMap;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
public interface IPlatformRoleService extends IService<PlatformRole> {
    /**
     * 获取角色名称
     * @param roleId
     * @return
     */
    PlatformRole getRoleById(int roleId);

    /**
     * 查询角色分页
     * @return
     */
    HashMap<String,Object> getRoleList(HashMap<String,Object> map);

    /**
     * 添加或修改角色
     * @param map
     * @return
     */
    int saveRole(HashMap<String,Object> map);

    /**
     * 删除角色
     * @param map
     * @return
     */
    void delRole(HashMap<String,Object> map);


}
