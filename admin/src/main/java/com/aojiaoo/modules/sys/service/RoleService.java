package com.aojiaoo.modules.sys.service;

import com.aojiaoo.modules.sys.entity.Role;
import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.modules.sys.mapper.RoleMapper;

import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role, RoleMapper> {

    public Role get(Integer id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    public boolean delete(Integer id) {
        return this.mapper.deleteByPrimaryKey(id) > 0;
    }
}
