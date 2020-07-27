package com.example.managerService.platform.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.bean.Constants;
import com.example.common.util.DateUtils;
import com.example.common.util.StringUtils;
import com.example.managerDao.platform.entity.PlatformMessageSystem;
import com.example.managerDao.platform.mapper.PlatformMessageSystemMapper;
import com.example.managerService.platform.IPlatformMessageSystemService;
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
 * @since 2020-07-23
 */
@Service
public class PlatformMessageSystemServiceImpl extends ServiceImpl<PlatformMessageSystemMapper, PlatformMessageSystem> implements IPlatformMessageSystemService {
    @Autowired
    PlatformMessageSystemMapper messageSystemMapper;
    /**
     * 查询未读数量
     * @return
     */
    @Override
    public int getMessageSystemCount() {

        return messageSystemMapper.selectCount(new LambdaQueryWrapper<PlatformMessageSystem>()
                .eq(PlatformMessageSystem::getIsHandle, Constants.ISREAD)
        );
    }

    /**
     * 系统消息列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getMessageSystemList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String messageTitle = map.get("messageTitle").toString();
        IPage<Map<String,Object>> messageSystemList = messageSystemMapper.selectMapsPage(pageN,new LambdaQueryWrapper<PlatformMessageSystem>()
                .like(org.apache.commons.lang.StringUtils.isNotBlank(messageTitle),PlatformMessageSystem::getMessageTitle,messageTitle)
                .orderByDesc(PlatformMessageSystem::getMessageTime)
        );
        List<Map<String,Object>> recordList = messageSystemList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
            String messageTime = DateUtils.stampToDate(recordMap.get("messageTime").toString());
            recordMap.put("messageTime",messageTime);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",messageSystemList.getTotal());
        return resultMap;
    }
}
