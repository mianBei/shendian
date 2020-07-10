package com.example.managerService.platformUser;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.PlatformRole;

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
}
