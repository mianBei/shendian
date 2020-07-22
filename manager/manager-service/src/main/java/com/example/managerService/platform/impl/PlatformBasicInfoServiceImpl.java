package com.example.managerService.platform.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.util.StringUtils;
import com.example.common.util.Util;
import com.example.managerDao.platform.entity.PlatformBasicInfo;
import com.example.managerDao.platform.mapper.PlatformBasicInfoMapper;
import com.example.managerService.platform.IPlatformBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-15
 */
@Service
public class PlatformBasicInfoServiceImpl extends ServiceImpl<PlatformBasicInfoMapper, PlatformBasicInfo> implements IPlatformBasicInfoService {
    @Autowired
    PlatformBasicInfoMapper basicInfoMapper;
    @Value("${pic.url_prefix}")
    private String url_prefix;
    /**
     * 平台列表查询
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getBasicInfoList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        IPage<Map<String,Object>> basicInfoList = basicInfoMapper.selectMapsPage(pageN,null);
        List<Map<String,Object>> recordList = basicInfoList.getRecords();
        DecimalFormat df = new DecimalFormat("0.00%");
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
            recordMap.put("shopWithdrawBatio",df.format(Double.parseDouble(recordMap.get("shopWithdrawBatio").toString())));
            recordMap.put("wxAppWithdrawBatio",df.format(Double.parseDouble(recordMap.get("wxAppWithdrawBatio").toString())));
            recordMap.put("agentWithdrawBatio",df.format(Double.parseDouble(recordMap.get("agentWithdrawBatio").toString())));
            recordMap.put("platformEarningsPercent",recordMap.get("platformEarningsPercent").toString()+"%");
            List<String> pics = Util.getWholePictureArrayPath(recordMap.get("platformLogo").toString(),"",",",url_prefix);
            recordMap.put("platformLogo",pics.get(0));
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",basicInfoList.getTotal());
        return resultMap;
    }

    /**
     * 根据ID查看平台
     * @param map
     * @return
     */
    @Override
    public HashMap<String,Object> getBasicInfoById(HashMap<String, Object> map) {
        Object platformId = map.get("platformId");
        PlatformBasicInfo basicInfo = new PlatformBasicInfo();
        HashMap<String,Object> resultMap = new HashMap<>();
        if (!"0".equals(platformId)){
            basicInfo = basicInfoMapper.selectOne(new LambdaQueryWrapper<PlatformBasicInfo>()
                    .eq(PlatformBasicInfo::getPlatformId,map.get("platformId"))
            );
            String logo = basicInfo.getPlatformLogo();
            String name = logo.substring(logo.lastIndexOf("/",logo.lastIndexOf("/")-1));
            resultMap.put("picName",name);
            List<String> pics = Util.getWholePictureArrayPath(basicInfo.getPlatformLogo(),"",",",url_prefix);
            basicInfo.setPlatformLogo(pics.get(0));
            basicInfo.setShopWithdrawBatio(basicInfo.getShopWithdrawBatio().stripTrailingZeros());
            basicInfo.setWxAppWithdrawBatio(basicInfo.getWxAppWithdrawBatio().stripTrailingZeros());
            basicInfo.setAgentWithdrawBatio(basicInfo.getAgentWithdrawBatio().stripTrailingZeros());
        }
        resultMap.put("basicInfo",basicInfo);
        return resultMap;
    }

    /**
     * 修改或者添加平台信息
     * @param map
     * @return
     */
    @Override
    public int saveBasicInfo(HashMap<String, Object> map) {
        int result = 0;
        Object basicInfoId = map.get("platformId");
        PlatformBasicInfo basicInfo = new PlatformBasicInfo();
        basicInfo.setPlatformName(map.get("name").toString());
        basicInfo.setPlatformLogo(map.get("logo").toString());
        basicInfo.setPlatformTel(map.get("tel").toString());
        basicInfo.setPlatformEarningsPercent(Integer.parseInt(map.get("earningsPercent").toString()));
        basicInfo.setVerificationCode(Integer.parseInt(map.get("verificationCode").toString()));
        basicInfo.setVerificationCodeTime(Integer.parseInt(map.get("verificationCodeTime").toString()));
        basicInfo.setShopWithdrawBatio(BigDecimal.valueOf(Double.parseDouble(map.get("shopWithdrawBatio").toString())));
        basicInfo.setWxAppWithdrawBatio(BigDecimal.valueOf(Double.parseDouble(map.get("wxAppWithdrawBatio").toString())));
        basicInfo.setAgentWithdrawBatio(BigDecimal.valueOf(Double.parseDouble(map.get("agentWithdrawBatio").toString())));
        if (basicInfoId ==null||"".equals(basicInfoId )){
            result = basicInfoMapper.insert(basicInfo);
        }else{
            basicInfo.setPlatformId(Integer.parseInt(map.get("platformId").toString()));
            result = basicInfoMapper.updateById(basicInfo);
        }
        return result;
    }
}
