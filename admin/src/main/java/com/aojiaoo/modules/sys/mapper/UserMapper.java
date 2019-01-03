package com.aojiaoo.modules.sys.mapper;

import com.aojiaoo.core.base.BaseMapper;
import com.aojiaoo.core.mybatis.annotations.MyBatisDao;
import com.aojiaoo.modules.sys.entity.User;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface UserMapper extends BaseMapper<User> {

    User selectByUserName(@Param("username") String username);


}