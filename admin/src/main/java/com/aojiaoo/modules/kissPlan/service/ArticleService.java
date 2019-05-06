package com.aojiaoo.modules.kissPlan.service;

import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.core.mybatis.plugins.paging.Page;
import com.aojiaoo.modules.kissPlan.entity.*;
import com.aojiaoo.modules.kissPlan.mapper.ArticleMapper;
import com.aojiaoo.modules.kissPlan.mapper.ArticleViewMapper;
import com.aojiaoo.modules.kissPlan.mapper.LikeRecordViewMapper;
import com.aojiaoo.utils.StringUtils;
import com.aojiaoo.utils.UserUtil;
import com.aojiaoo.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService extends BaseService<Article, ArticleMapper> {

    @Resource
    private ArticleViewMapper articleViewMapper;

    @Resource
    private LikeRecordViewMapper likeRecordViewMapper;


    @Autowired
    private CommentService commentService;


    @Transactional
    public boolean save(Article article) {

        String content = StringUtils.trimToEmpty(article.getContent());
        if (StringUtils.isBlank(article.getPreview())) {
            String preview = content.length() > 15 ? content.substring(0, 15) + "..." : content;
            article.setPreview(preview);
        }
        article.setContent(content);
        article.setAuthorId(UserUtil.getCurrentUserId());
        return super.save(article);
    }


    /**
     * 查看文章详情
     *
     * @param id
     * @return
     */
    public ArticleView getArticleView(Integer id) {

        if (id == null || id <= 0) {
            return null;
        }
        ArticleView articleView = articleViewMapper.selectByPrimaryKey(id);
        this.setLikedStatus(articleView);
        return articleView;
    }


    /*得到加入轮播图的 文章 */
    public List<Map<String, Object>> getSlideshowArticle(int size) {
        size = (size < 2 || size > 6) ? 5 : size;
        List<ArticleView> list = this.articleViewMapper.getHotArticle(size);
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> resList = new ArrayList<>();
        list.forEach(article -> {
            map.put("img", article.getFirstImg());
            map.put("url", this.getArticleUrlById(article.getId()));
            map.put("articleId", article.getId());
            resList.add(map);
        });
        return resList;
    }

    /**
     * 首页文章列表
     *
     * @param page
     * @return
     */
    public Page<ArticleView> indexArticleList(Page<ArticleView> page, Integer authorId) {
        page.setList(this.articleViewMapper.indexArticleList(page, authorId));
        return page;
    }


    /**
     * 发表评论
     *
     * @param entity
     * @return
     */
    public boolean reply(Comment entity) {

        if (this.get(entity.getArticleId()) == null) {
            logger.warn("试图id为{}的文章不存在", entity.getArticleId());
            return false;
        }

        entity.setAuthorId(UserUtil.getCurrentUserId());
        this.mapper.addCommentNumById(entity.getArticleId());
        return commentService.insert(entity);
    }


    public boolean doLike(Integer id, boolean isCancel) {

        boolean isLiked = this.mapper.checkIsLiked(id, UserUtil.getCurrentUserId());

        if ((isLiked && !isCancel) || (!isLiked && isCancel)) {
            //不需要操作
            return true;
        }

        if (isCancel) {
            return this.mapper.cancelLike(id, UserUtil.getCurrentUserId()) > 0;
        } else {
            return this.mapper.doLike(id, UserUtil.getCurrentUserId()) > 0;
        }
    }

    //设置当前用户的是否点赞属性
    private void setLikedStatus(ArticleView articleView) {
        if (!UserUtil.isAuthenticated()) {
            return;
        }

        boolean isLiked = this.mapper.checkIsLiked(articleView.getId(), UserUtil.getCurrentUserId());
        articleView.setIsCurrentUserLiked(isLiked);
    }


    private String getArticleUrlById(Integer id) {
        return WebUtils.spliceUrl(WebUtils.getUrl(), "article", String.valueOf(id));
    }


    public Page<LikeRecordView> getLikeRecord(LikeRecordView likeRecordView, Page<LikeRecordView> page) {
        List<LikeRecordView> likeRecordViews = this.likeRecordViewMapper.selectBySelective(page, likeRecordView);
        page.setList(likeRecordViews);
        return page;
    }
}
