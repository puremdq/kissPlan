package com.aojiaoo.modules.sys.service;

import com.aojiaoo.modules.sys.entity.Log;
import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.modules.sys.mapper.LogMapper;

import org.springframework.stereotype.Service;

@Service
public class LogService extends BaseService<Log, LogMapper> {

    public Log get(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    public boolean delete(Long id) {
        return this.mapper.deleteByPrimaryKey(id) > 0;
    }
}
