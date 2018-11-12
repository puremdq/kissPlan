package com.aojiaoo.modules.sys.mapper;

import java.util.List;
import com.aojiaoo.core.base.BaseMapper;
import com.aojiaoo.modules.sys.entity.Menu;

import com.aojiaoo.core.mybatis.annotations.MyBatisDao;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface MenuMapper extends BaseMapper<Menu> {


    Menu selectByPrimaryKey(@Param("id") Integer id);

    int deleteByPrimaryKey(@Param("id") Integer id);

}