package com.aojiaoo.modules.sys.service;

import com.aojiaoo.modules.sys.entity.Menu;
import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.modules.sys.mapper.MenuMapper;

import org.springframework.stereotype.Service;

@Service
public class MenuService extends BaseService<Menu, MenuMapper> {

    public Menu get(Integer id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    public boolean delete(Integer id) {
        return this.mapper.deleteByPrimaryKey(id) > 0;
    }
}
