package com.aojiaoo.modules.sys.mapper;

import com.aojiaoo.core.base.BaseMapper;
import com.aojiaoo.modules.sys.entity.OperateLog;

import com.aojiaoo.core.mybatis.annotations.MyBatisDao;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface OperateLogMapper extends BaseMapper<OperateLog> {


    OperateLog selectByPrimaryKey(@Param("id") Long id);

    int deleteByPrimaryKey(@Param("id") Long id);

}