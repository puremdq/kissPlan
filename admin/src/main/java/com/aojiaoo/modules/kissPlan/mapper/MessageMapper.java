package com.aojiaoo.modules.kissPlan.mapper;

import com.aojiaoo.core.base.BaseMapper;
import com.aojiaoo.modules.kissPlan.entity.Message;

import com.aojiaoo.core.mybatis.annotations.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@MyBatisDao
public interface MessageMapper extends BaseMapper<Message> {


    Integer getUnreadNum(Integer currentUserId);

    //    <!--设置某个用户下的消息全部为已读-->
    Integer setReadByUserId(Integer userId);

    List<Message> messageList(@Param("userId") Integer userId, @Param("maxSendTime") Date maxSendTime);
}