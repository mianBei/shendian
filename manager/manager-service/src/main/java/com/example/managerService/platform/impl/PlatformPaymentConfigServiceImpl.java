package com.example.managerService.platform.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.StringUtils;
import com.example.managerDao.platform.entity.PlatformPaymentConfig;
import com.example.managerDao.platform.mapper.PlatformPaymentConfigMapper;
import com.example.managerService.platform.IPlatformPaymentConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-24
 */
@Service
public class PlatformPaymentConfigServiceImpl extends ServiceImpl<PlatformPaymentConfigMapper, PlatformPaymentConfig> implements IPlatformPaymentConfigService {
    @Autowired
    PlatformPaymentConfigMapper paymentConfigMapper;

    /**
     * 付款配置列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getPaymentConfigList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        IPage<Map<String,Object>> paymentConfigList = paymentConfigMapper.selectMapsPage(pageN, null);
        List<Map<String,Object>> recordList = paymentConfigList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",paymentConfigList.getTotal());
        return resultMap;
    }

    /**
     * 根据id查询付款设置
     * @return
     */
    @Override
    public PlatformPaymentConfig getPaymentConfig() {

        return paymentConfigMapper.selectOne(null);
    }

    /**
     * 修改付款配置
     * @param map
     * @return
     */
    @Override
    public int savePaymentConfig(HashMap<String, Object> map) {
        int result = 0;
        Object paymentConfigId = map.get("paymentConfigId");
        PlatformPaymentConfig paymentConfig = new PlatformPaymentConfig();
        paymentConfig.setUserType(Integer.parseInt(map.get("userType").toString()));
        paymentConfig.setAppType(Integer.parseInt(map.get("appType").toString()));
        paymentConfig.setPaymentConfigData(map.get("paymentConfigData").toString());
        paymentConfig.setRemark(map.get("remark").toString());
        paymentConfig.setToDayWithdrawAMT(BigDecimal.valueOf(Double.parseDouble(map.get("toDayWithdrawAMT").toString())));
        if (paymentConfigId==null && ("").equals(paymentConfigId)){
            result = paymentConfigMapper.insert(paymentConfig);
        }else{
            paymentConfig.setPaymentConfigId(Integer.parseInt(map.get("paymentConfigId").toString()));
            result = paymentConfigMapper.updateById(paymentConfig);
        }
        return result;
    }
}
