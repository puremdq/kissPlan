package com.aojiaoo.modules.kissPlan.service;

import com.aojiaoo.modules.kissPlan.entity.Article;
import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.modules.kissPlan.mapper.ArticleMapper;

import com.aojiaoo.utils.StringUtils;
import com.aojiaoo.utils.UserUtil;
import com.aojiaoo.utils.WebUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService extends BaseService<Article, ArticleMapper> {

    public Article get(Integer id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Transactional
    public boolean delete(Integer id) {
        return this.mapper.deleteByPrimaryKey(id) > 0;
    }


    @Transactional
    public boolean save(Article article) {

        String content = StringUtils.trimToEmpty(article.getContent());
        String preview = content.length() > 15 ? content.substring(0, 15) + "..." : content;
        article.setContent(content);
        article.setAuthorId(UserUtil.getCurrentUser().getId());
        article.setPreview(preview);
        return super.save(article);
    }


    /*得到加入轮播图的 文章 */
    public List<Map<String, String>> getSlideshowArticle(int size) {
        size = (size < 2 || size > 6) ? 5 : size;
        List<Article> list = this.mapper.getHotArticle(size);

        Map<String, String> map = new HashMap<>();
        List<Map<String, String>> resList = new ArrayList<>();
        list.forEach(article -> {
            map.put("url", getArticleUrlById(article.getId()));
            map.put("img", article.getFirstImg());
            resList.add(map);
        });
        return resList;
    }

    private String getArticleUrlById(Integer id) {
        return WebUtils.spliceUrl(WebUtils.getUrl(), "article", String.valueOf(id));
    }
}
