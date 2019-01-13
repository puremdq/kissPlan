package com.aojiaoo.modules.kissPlan.mapper;

import com.aojiaoo.core.base.BaseMapper;
import com.aojiaoo.modules.kissPlan.entity.Article;

import com.aojiaoo.core.mybatis.annotations.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> getHotArticle(@Param("size") int size);
}