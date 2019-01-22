package com.aojiaoo.core.base;

import com.aojiaoo.core.mybatis.plugins.paging.Page;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface BaseMapper<Entity extends BaseEntity> extends Mapper<Entity> {


    List<Entity> selectBySelective(Entity record);

    List<Entity> selectBySelective(Entity record, Page page);


    Map selectMap(Entity record);


}
