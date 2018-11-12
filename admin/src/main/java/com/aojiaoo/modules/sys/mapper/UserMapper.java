package com.aojiaoo.modules.sys.mapper;

import com.aojiaoo.core.base.BaseMapper;
import com.aojiaoo.core.mybatis.annotations.MyBatisDao;
import com.aojiaoo.modules.sys.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface UserMapper extends BaseMapper<User> {

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectBySelective(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUserName(@Param("username") String username);

    int deleteByPrimaryKey(Integer id);

}