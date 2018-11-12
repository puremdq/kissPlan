package com.aojiaoo.modules.sys.service;

import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.modules.sys.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, UserMapper> {

    public User getByUserName(String username) {
        return this.mapper.selectByUserName(username);
    }

    public User get(Integer id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    public boolean delete(Integer id) {
        return this.mapper.deleteByPrimaryKey(id) > 0;
    }
}
