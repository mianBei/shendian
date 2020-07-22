package com.example.managerService.platform.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.bean.Constants;
import com.example.common.util.DateUtils;
import com.example.common.util.StringUtils;
import com.example.common.util.Util;
import com.example.managerDao.platform.entity.PlatformMessagePost;
import com.example.managerDao.platform.mapper.PlatformMessagePostMapper;
import com.example.managerService.platform.IPlatformMessagePostService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * @since 2020-07-20
 */
@Service
public class PlatformMessagePostServiceImpl extends ServiceImpl<PlatformMessagePostMapper, PlatformMessagePost> implements IPlatformMessagePostService {
    @Value("${pic.url_prefix}")
    private String url_prefix;
    @Autowired
    PlatformMessagePostMapper messagePostMapper;
    /**
     * 平台消息列表
     * @param map
     * @return
     */
    @Override
    public HashMap<String, Object> getMessagePostList(HashMap<String, Object> map) {
        int page=Integer.parseInt(StringUtils.catchNull(map.get("page"), "1"));
        int rows=Integer.parseInt(StringUtils.catchNull(map.get("rows"), "10"));
        int start=(page-1)*rows;
        IPage<Map<String, Object>> pageN = new Page<>(page, rows);
        String messageTitle = map.get("messageTitle").toString();
        IPage<Map<String,Object>> messagePostList = messagePostMapper.selectMapsPage(pageN,new LambdaQueryWrapper<PlatformMessagePost>()
            .like(org.apache.commons.lang.StringUtils.isNotBlank(messageTitle),PlatformMessagePost::getMessageTitle,messageTitle)
                .orderByDesc(PlatformMessagePost::getIsDel)
        );
        List<Map<String,Object>> recordList = messagePostList.getRecords();
        for (Map<String,Object> recordMap: recordList) {
            recordMap.put("index", ++start);
            Object messageCoverImage = recordMap.get("messageCoverImage");
            if (messageCoverImage!=null&&messageCoverImage!=""){
                List<String> pics = Util.getWholePictureArrayPath(messageCoverImage.toString(),"",",",url_prefix);
                recordMap.put("messageCoverImage",pics.get(0));
            }
            String messageTime = DateUtils.stampToDate(recordMap.get("messageTime").toString());
            recordMap.put("messageTime",messageTime);
        }
        HashMap<String,Object> resultMap = new HashMap<>();
        resultMap.put("result",recordList);
        resultMap.put("totalCount",messagePostList.getTotal());
        return resultMap;
    }

    /**
     * 根据id查询
     * @param map
     * @return
     */
    @Override
    public HashMap<String,Object> getMessagePostById(HashMap<String, Object> map) {
        Object messagePostId = map.get("postMessageId");
        PlatformMessagePost messagePost = new PlatformMessagePost();
        HashMap<String,Object> resultMap = new HashMap<>();
        if (!"0".equals(messagePostId)){
            messagePost = messagePostMapper.selectOne(new LambdaQueryWrapper<PlatformMessagePost>()
                    .eq(PlatformMessagePost::getPostMessageId,map.get("postMessageId"))
            );
            String logo = messagePost.getMessageCoverImage();
            String name = logo.substring(logo.lastIndexOf("/",logo.lastIndexOf("/")-1));
            resultMap.put("picName",name);
            List<String> pics = Util.getWholePictureArrayPath(messagePost.getMessageCoverImage(),"",",",url_prefix);
            messagePost.setMessageCoverImage(pics.get(0));
            String userArr = messagePost.getPostUserArr();
            JSONArray jsonArray = JSONArray.fromObject(userArr);
            resultMap.put("arrList",jsonArray);
        }
        resultMap.put("messagePost",messagePost);
        return resultMap;
    }

    /**
     * 获取json字符的
     * @param arr
     * @param arrName
     */
    /*public void getArrString(String arr,String arrName){
        JSONArray jsonArray = JSONArray.fromObject(arr);
        List<HashMap<String,Object>> userList = JSONArray.toList(jsonArray,new HashMap<String,Object>(), new JsonConfig());
        String userString="";
        for (HashMap<String,Object> userMap:userList) {
            userString += userMap.get(arrName)+",";
        }
        userString.substring(0,userString.length()-1);
    }*/
    /**
     * 添加消息推送
     * @param map
     * @return
     */
    @Override
    public int saveMessagePost(HashMap<String, Object> map) {
        //Object postMessageId = map.get("postMessageId");
        PlatformMessagePost messagePost = new PlatformMessagePost();
        messagePost.setMessageCoverImage(map.get("messageCoverImage").toString());
        messagePost.setMessageAbout(map.get("messageAbout").toString());
        messagePost.setMessageObject(Integer.parseInt(map.get("messageObject").toString()));
        messagePost.setMessageData(map.get("messageData").toString());
        messagePost.setMessageTime(System.currentTimeMillis()/1000L);
        messagePost.setMessageTitle(map.get("messageTitle").toString());
        messagePost.setPlatformId(Integer.parseInt(map.get("platformId").toString()));
        Object arrObject = map.get("arrObject");
        if (arrObject!=null){
            String[] arrString = arrObject.toString().split(",");
            JSONArray jsonArray = new JSONArray();
            int messageObject = Integer.parseInt(map.get("messageObject").toString());
            for (String arr:arrString) {
                HashMap<String,Object> arrMap = new HashMap<>();
                if (4==messageObject){
                    arrMap.put("userId",arr);
                }else if (5==messageObject){
                    arrMap.put("shopId",arr);
                }else if (9==messageObject){
                    arrMap.put("agentId",arr);
                }
                arrMap.put("isRead", Constants.ISREAD);
                arrMap.put("isDel",Constants.ISDEL);
                jsonArray.add(arrMap);
            }
            if (4==messageObject){
                messagePost.setPostUserArr(jsonArray.toString());
            }else if (5==messageObject){
                messagePost.setPostShopArr(jsonArray.toString());
            }else if (9==messageObject){
                messagePost.setPostAgentArr(jsonArray.toString());
            }

        }
        messagePost.setPostPlatformUserId(map.get("platformAccountUserId").toString());
        /*int result = 0;
        if (postMessageId==null&&"".equals(postMessageId)){
            result= messagePostMapper.insert(messagePost);
        }else{
            messagePost.setPostMessageId(Integer.parseInt(map.get("postMessageId").toString()));
            result = messagePostMapper.updateById(messagePost);
        }*/
        return messagePostMapper.insert(messagePost);
    }

    /**
     * 删除消息
     * @param map
     * @return
     */
    @Override
    public int delMessagePost(HashMap<String, Object> map) {
        PlatformMessagePost messagePost = new PlatformMessagePost();
        messagePost.setPostMessageId(Integer.parseInt(map.get("postMessageId").toString()));
        messagePost.setIsDel(Constants.ISDELYES);
        int result = messagePostMapper.updateById(messagePost);
        return result;
    }
}
