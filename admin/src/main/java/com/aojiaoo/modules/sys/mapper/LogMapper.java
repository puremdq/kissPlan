package com.aojiaoo.modules.sys.mapper;

import java.util.List;
import com.aojiaoo.core.base.BaseMapper;
import com.aojiaoo.modules.sys.entity.Log;

import com.aojiaoo.core.mybatis.annotations.MyBatisDao;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface LogMapper extends BaseMapper<Log> {


    Log selectByPrimaryKey(@Param("id") Long id);

    int deleteByPrimaryKey(@Param("id") Long id);

}