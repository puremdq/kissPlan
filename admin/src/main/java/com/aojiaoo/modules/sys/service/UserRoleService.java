package com.aojiaoo.modules.sys.service;

import com.aojiaoo.modules.sys.entity.UserRole;
import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.modules.sys.mapper.UserRoleMapper;

import org.springframework.stereotype.Service;

@Service
public class UserRoleService extends BaseService<UserRole, UserRoleMapper> {

    public UserRole get(Integer userId, Integer roleId) {
        return this.mapper.selectByPrimaryKey(userId, roleId);
    }

    public boolean delete(Integer userId, Integer roleId) {
        return this.mapper.deleteByPrimaryKey(userId, roleId) > 0;
    }
}
