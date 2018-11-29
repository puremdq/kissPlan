package com.aojiaoo.extra.generator;

import com.aojiaoo.utils.FileUtils;
import com.aojiaoo.utils.FreemakerUtil;
import com.aojiaoo.utils.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneratorWord {


    /**
     * @return entityName 生成的实体名称
     */
    public static void generatorWord() throws Exception {
        Configuration cfg = getConfiguration();


        List<Map<String, String>> articleList = new ArrayList<>();
        Map<String, String> article = new HashMap<>();
        article.put("title", "文章标题");
        article.put("publishDate", "2018-11-07");
        article.put("content", "文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容");
        article.put("mediaName", "人民日报");
        articleList.add(article);

        article = new HashMap<>();
        article.put("title", "文章标题");
        article.put("publishDate", "2018-11-07");
        article.put("content", "文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容文章内容");
        article.put("mediaName", "人民日报");
        articleList.add(article);

        Map<String, Object> templateMap = new HashMap<>();
        templateMap.put("articleList", articleList);
        String filePath = FileUtils.spliceFilePath("c:/project", "test.doc");
        Template template = cfg.getTemplate("简报 - 模板.ftl");
        template.process(templateMap, new FileWriter(filePath));
    }


    private static Configuration getConfiguration() {
        Configuration cfg = FreemakerUtil.getConfiguration();
        try {
            FreemakerUtil.setStaticPacker(cfg, "StringUtils", StringUtils.class.getName());
            cfg.setDirectoryForTemplateLoading(new File(FileUtils.getFilePathByClasspathOrSelf("classpath:extra/template/word")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cfg;
    }

    public static void main(String[] args) throws Exception {
        generatorWord();
    }


}
