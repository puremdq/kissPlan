package com.aojiaoo.modules.kissPlan.service;

import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.core.mybatis.plugins.paging.Page;
import com.aojiaoo.modules.kissPlan.entity.Article;
import com.aojiaoo.modules.kissPlan.entity.ArticleView;
import com.aojiaoo.modules.kissPlan.mapper.ArticleMapper;
import com.aojiaoo.modules.kissPlan.mapper.ArticleViewMapper;
import com.aojiaoo.utils.StringUtils;
import com.aojiaoo.utils.UserUtil;
import com.aojiaoo.utils.WebUtils;
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


    @Transactional
    public boolean save(Article article) {

        String content = StringUtils.trimToEmpty(article.getContent());
        String preview = content.length() > 15 ? content.substring(0, 15) + "..." : content;
        article.setContent(content);
        article.setAuthorId(UserUtil.getCurrentUser().getId());
        article.setPreview(preview);
        return super.save(article);
    }


    public ArticleView getArticleView(Integer id) {

        if (id == null || id <= 0) {
            return null;
        }
        return articleViewMapper.selectByPrimaryKey(id);
    }


    /*得到加入轮播图的 文章 */
    public List<Map<String, String>> getSlideshowArticle(int size) {
        size = (size < 2 || size > 6) ? 5 : size;
        List<ArticleView> list = this.articleViewMapper.getHotArticle(size);
        Map<String, String> map = new HashMap<>();
        List<Map<String, String>> resList = new ArrayList<>();
        list.forEach(article -> {
            map.put("url", getArticleUrlById(article.getId()));
            map.put("img", article.getFirstImg());
            resList.add(map);
        });
        return resList;
    }


    public Page<ArticleView> indexArticleList(Page<ArticleView> page) {
        page.setList(this.articleViewMapper.indexArticleList(page));
        return page;
    }

    private String getArticleUrlById(Integer id) {
        return WebUtils.spliceUrl(WebUtils.getUrl(), "article", String.valueOf(id));
    }


}
