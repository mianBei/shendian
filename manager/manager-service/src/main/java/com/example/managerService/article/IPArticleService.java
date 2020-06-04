package com.example.managerService.article;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.managerDao.article.entity.PArticle;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-05-07
 */
public interface IPArticleService extends IService<PArticle> {

    /**
     * 创建lucene索引文件
     * @return
     */
    String createLucene();
}
