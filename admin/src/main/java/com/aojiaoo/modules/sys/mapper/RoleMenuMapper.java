package com.aojiaoo.modules.sys.mapper;

import com.aojiaoo.core.base.BaseMapper;
import com.aojiaoo.core.mybatis.annotations.MyBatisDao;
import com.aojiaoo.modules.sys.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {


    RoleMenu selectByPrimaryKey(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    int deleteByPrimaryKey(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

}