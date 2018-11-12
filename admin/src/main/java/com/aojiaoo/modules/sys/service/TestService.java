package com.aojiaoo.modules.sys.service;

import com.aojiaoo.modules.sys.entity.Test;
import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.modules.sys.mapper.TestMapper;

import org.springframework.stereotype.Service;

@Service
public class TestService extends BaseService<Test, TestMapper> {

    public Test get(String id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    public boolean delete(String id) {
        return this.mapper.deleteByPrimaryKey(id) > 0;
    }
}
