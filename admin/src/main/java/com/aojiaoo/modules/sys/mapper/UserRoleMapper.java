package com.aojiaoo.modules.sys.mapper;

import java.util.List;
import com.aojiaoo.core.base.BaseMapper;
import com.aojiaoo.modules.sys.entity.UserRole;

import com.aojiaoo.core.mybatis.annotations.MyBatisDao;
import org.apache.ibatis.annotations.Param;
@MyBatisDao
public interface UserRoleMapper extends BaseMapper<UserRole> {


    UserRole selectByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

}