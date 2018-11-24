package com.aojiaoo.modules.sys.service;

import com.aojiaoo.modules.sys.entity.OperateLog;
import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.modules.sys.mapper.OperateLogMapper;

import org.springframework.stereotype.Service;

@Service
public class OperateLogService extends BaseService<OperateLog, OperateLogMapper> {

    public OperateLog get(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    public boolean delete(Long id) {
        return this.mapper.deleteByPrimaryKey(id) > 0;
    }
}
