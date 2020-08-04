package com.example.managerService.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.StringUtils;
import com.example.managerDao.user.entity.UserComplaint;
import com.example.managerDao.user.mapper.UserComplaintMapper;
import com.example.managerService.user.IUserComplaintService;
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
 * @since 2020-08-03
 */
@Service
public class UserComplaintServiceImpl extends ServiceImpl<UserComplaintMapper, UserComplaint> implements IUserComplaintService {

    @Autowired
    UserComplaintMapper complaintMapper;

    /**
     * 公司列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getComplaintList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String linkName = map.get("linkName").toString();
        IPage<Map<String,Object>> basicInfoList = complaintMapper.selectMapsPage(pageN,new LambdaQueryWrapper<UserComplaint>()
                .like(org.apache.commons.lang.StringUtils.isNotBlank(linkName),UserComplaint::getLinkName,linkName)
                .orderByDesc(UserComplaint::getComplainTime)
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

    /**
     * 根据id获取投诉内容
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getComplaintById(HashMap<String, Object> map) {
        String complaintId = map.get("complainId").toString();
        UserComplaint complaint = complaintMapper.selectById(complaintId);
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("complaint",complaint);
        return resultMap;
    }

    /**
     * 修改投诉内容
     * @param map
     * @return
     */
    @Override
    public int saveComplaint(HashMap<String, Object> map) {
        String comlaintId = map.get("complainId").toString();
        UserComplaint complaint = new UserComplaint();
        complaint.setIsDispose(Integer.parseInt(map.get("isDispose").toString()));
        complaint.setIsDel(Integer.parseInt(map.get("isDel").toString()));
        complaint.setDisposeResult(map.get("disposeResult").toString());
        complaint.setBindPlatformUserId(map.get("platformAccountUserId").toString());
        complaint.setDelCause(map.get("delCause").toString());
        complaint.setDelType(Integer.parseInt(map.get("delType").toString()));
        complaint.setComplainId(Long.parseLong(comlaintId));

        return complaintMapper.updateById(complaint);
    }
}
