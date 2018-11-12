package com.aojiaoo.modules.sys.mapper;

import java.util.List;

import com.aojiaoo.core.base.BaseMapper;
import com.aojiaoo.modules.sys.entity.Test;

import com.aojiaoo.core.mybatis.annotations.MyBatisDao;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface TestMapper extends BaseMapper<Test> {

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(@Param("id") String id);

    List<Test> selectBySelective(Test record);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);

    int deleteByPrimaryKey(@Param("id") String id);

}