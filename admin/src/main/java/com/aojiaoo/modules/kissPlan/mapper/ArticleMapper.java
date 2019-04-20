package com.aojiaoo.modules.kissPlan.mapper;

import com.aojiaoo.core.base.BaseMapper;
import com.aojiaoo.core.mybatis.annotations.MyBatisDao;
import com.aojiaoo.modules.kissPlan.entity.Article;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface ArticleMapper extends BaseMapper<Article> {


    boolean checkIsLiked(@Param("articleId") Integer articleId, @Param("userId") Integer userId);

    /**
     * 新增点赞
     *
     * @param articleId     articleId
     * @param userId userId
     * @return
     */
    Integer doLike(@Param("articleId") Integer articleId, @Param("userId") Integer userId);

    /**
     * 取消点赞
     *
     * @param articleId     articleId
     * @param userId userId
     * @return
     */
    Integer cancelLike(@Param("articleId") Integer articleId, @Param("userId") Integer userId);

    void addCommentNumById(Integer articleId);
}