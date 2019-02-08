package com.aojiaoo.modules.kissPlan.service;

import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.modules.kissPlan.entity.ArticleView;
import com.aojiaoo.modules.kissPlan.entity.Author;
import com.aojiaoo.modules.kissPlan.entity.AuthorView;
import com.aojiaoo.modules.kissPlan.mapper.AuthorMapper;
import com.aojiaoo.modules.kissPlan.mapper.AuthorViewMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthorService extends BaseService<Author, AuthorMapper> {

    @Resource
    private AuthorViewMapper authorViewMapper;


    public AuthorView getAuthorView(Integer id) {

        if (id == null || id <= 0) {
            return null;
        }
        return authorViewMapper.selectByPrimaryKey(id);
    }

}
