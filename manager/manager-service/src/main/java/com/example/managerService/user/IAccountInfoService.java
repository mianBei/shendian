package com.example.managerService.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.user.entity.AccountInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-06-29
 */
public interface IAccountInfoService extends IService<AccountInfo> {

    List<AccountInfo> getAccountList();
}
