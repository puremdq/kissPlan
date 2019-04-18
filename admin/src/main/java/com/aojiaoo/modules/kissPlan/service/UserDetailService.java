package com.aojiaoo.modules.kissPlan.service;

import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.modules.kissPlan.entity.UserView;
import com.aojiaoo.modules.kissPlan.entity.UserDetail;
import com.aojiaoo.modules.kissPlan.mapper.UserDetailMapper;
import com.aojiaoo.modules.kissPlan.mapper.UserViewMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailService extends BaseService<UserDetail, UserDetailMapper> {

    @Resource
    private UserViewMapper authorViewMapper;


    public UserView getAuthorView(Integer id) {

        if (id == null || id <= 0) {
            return null;
        }
        return authorViewMapper.selectByPrimaryKey(id);
    }

}
