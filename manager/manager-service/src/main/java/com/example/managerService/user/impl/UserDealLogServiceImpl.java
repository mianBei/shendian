package com.example.managerService.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.StringUtils;
import com.example.managerDao.user.entity.UserDealLog;
import com.example.managerDao.user.mapper.UserDealLogMapper;
import com.example.managerService.user.IUserDealLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-08-03
 */
@Service
public class UserDealLogServiceImpl extends ServiceImpl<UserDealLogMapper, UserDealLog> implements IUserDealLogService {

    @Autowired
    UserDealLogMapper dealLogMapper;
    /**
     * 交易记录列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getDealLogList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String dealLogTitle = map.get("dealLogTitle").toString();
        IPage<Map<String,Object>> basicInfoList = dealLogMapper.selectMapsPage(pageN,new LambdaQueryWrapper<UserDealLog>()
                .like(org.apache.commons.lang.StringUtils.isNotBlank(dealLogTitle),UserDealLog::getDealLogTitle,dealLogTitle)
                .orderByDesc(UserDealLog::getAddTime)
        );
        List<Map<String,Object>> recordList = basicInfoList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",basicInfoList.getTotal());
        return resultMap;
    }
}
