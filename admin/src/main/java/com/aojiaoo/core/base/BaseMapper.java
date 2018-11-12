package com.aojiaoo.core.base;

import com.aojiaoo.core.mybatis.plugins.paging.Page;

import java.util.List;

public interface BaseMapper<Entity extends BaseEntity> {

    int insert(Entity record);

    int insertSelective(Entity record);

    Entity selectByPrimaryKey(Entity record);

    int updateByPrimaryKeySelective(Entity record);

    int updateByPrimaryKey(Entity record);

    List<Entity> selectBySelective(Entity record);

    List<Entity> selectBySelective(Entity record, Page page);

    int deleteByPrimaryKey(Entity record);

}
