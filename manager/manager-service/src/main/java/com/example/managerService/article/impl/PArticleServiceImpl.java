package com.example.managerService.article.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.lucene.luceneUtil;
import com.example.managerDao.article.entity.PArticle;
import com.example.managerDao.article.mapper.PArticleMapper;
import com.example.managerService.article.IPArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-05-07
 */
@Service
public class PArticleServiceImpl extends ServiceImpl<PArticleMapper, PArticle> implements IPArticleService {

    @Autowired
    PArticleMapper articleMapper;
    /**
     * 创建文章索引库
     * @return
     */
    @Override
    public String createLucene() {
        List<PArticle> articleList = articleMapper.selectList(null);
        HashMap<String,String> resultMap = new HashMap<>();
        List<HashMap<String,String>> resultList = new ArrayList<>();
        for (PArticle article:articleList) {
            resultMap.put("title",article.getTitle());
            resultMap.put("content",article.getContent());
            resultList.add(resultMap);
        }
        luceneUtil lucene = new luceneUtil();
        lucene.createLuceneWrite(resultList);
        return null;
    }
}
