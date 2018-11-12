package com.aojiaoo.modules.sys.service;

import com.aojiaoo.modules.sys.entity.RoleMenu;
import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.modules.sys.mapper.RoleMenuMapper;

import org.springframework.stereotype.Service;

@Service
public class RoleMenuService extends BaseService<RoleMenu, RoleMenuMapper> {

    public RoleMenu get(Integer roleId, Integer menuId) {
        return this.mapper.selectByPrimaryKey(roleId, menuId);
    }

    public boolean delete(Integer roleId, Integer menuId) {
        return this.mapper.deleteByPrimaryKey(roleId, menuId) > 0;
    }
}
