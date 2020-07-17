package com.example.managerService.jurisdiction.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.StringUtils;
import com.example.managerDao.user.entity.PlatformAccount;
import com.example.managerDao.user.entity.PlatformRole;
import com.example.managerDao.user.mapper.PlatformAccountMapper;
import com.example.managerService.jurisdiction.IPlatformAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-09
 */
@Service
public class PlatformAccountServiceImpl extends ServiceImpl<PlatformAccountMapper, PlatformAccount> implements IPlatformAccountService {

    @Autowired
    PlatformAccountMapper platformAccountMapper;

    /**
     * 根据code获取用户信息
     * @param code
     * @return
     */
    @Override
    public PlatformAccount getAccountByCode(String code) {
        return platformAccountMapper.selectOne(new LambdaQueryWrapper<PlatformAccount>()
                .eq(PlatformAccount::getPlatformAccountCode,code)
        );
    }

    /**
     * 查询用户列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getAccountList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String phone = map.get("phone").toString();
        IPage<Map<String,Object>> platformAccountList = platformAccountMapper.selectMapsPage(pageN,new LambdaQueryWrapper<PlatformAccount>()
                .eq(StringUtils.isNotBlank(phone),PlatformAccount::getPlatformAccountPhoneCode,phone)
        );
        List<Map<String,Object>> recordList = platformAccountList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",platformAccountList.getTotal());
        return resultMap;
    }
}
