package com.aojiaoo.modules.kissPlan.mapper;

import com.aojiaoo.core.base.BaseMapper;
import com.aojiaoo.core.mybatis.annotations.MyBatisDao;
import com.aojiaoo.core.mybatis.plugins.paging.Page;
import com.aojiaoo.modules.kissPlan.entity.ArticleView;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface ArticleViewMapper extends BaseMapper<ArticleView> {

    List<ArticleView> indexArticleList(Page<ArticleView> page, @Param("authorId") Integer authorId);

    List<ArticleView> getHotArticle(@Param("size") int size);

}