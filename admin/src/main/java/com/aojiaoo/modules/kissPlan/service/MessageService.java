package com.aojiaoo.modules.kissPlan.service;

import com.aojiaoo.core.base.BaseService;
import com.aojiaoo.core.common.GlobalProperties;
import com.aojiaoo.modules.kissPlan.entity.Message;
import com.aojiaoo.modules.kissPlan.mapper.MessageMapper;
import com.aojiaoo.utils.CacheUtils;
import com.aojiaoo.utils.IdUtil;
import com.aojiaoo.utils.StringUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class MessageService extends BaseService<Message, MessageMapper> {

    /*
     * 用户发消息
     * */
    public void sendMessage(SimpMessagingTemplate simpMessagingTemplate, Message message, Principal principal) {

        message.setIsRead(GlobalProperties.FALSE);
        message.setFrom(Integer.valueOf(principal.getName()));
        message.setCreateBy(message.getFrom());
        message.setUpdateBy(message.getFrom());

        if (!IdUtil.isValidId(message.getTo())) {
            logger.error("非法的操作,发送的给的目标[id={}]无效", message.getTo());
            return;
        }

        if (CacheUtils.get(GlobalProperties.CONNECTED_SOCKET_USER_CACHE_NAME, StringUtils.toString(message.getTo())) == null) {
            logger.debug("发送的给的目标[id={}]不在线，将消息直接写入数据库", message.getTo());
            this.insert(message);
            return;
        }

        logger.debug("发送的给的目标[id={}]在线,开始发送。。。", message.getTo());
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getTo()), "/receive/msg", message);
        message.setIsRead(GlobalProperties.TRUE);//设置已读
        this.insert(message);

    }

    /**
     * 查看某个用户的未读数
     *
     * @param currentUserId userId
     * @return
     */
    public Integer getUnreadNum(Integer currentUserId) {
        return this.mapper.getUnreadNum(currentUserId);
    }

    /**
     * 查看某个用户的消息列表
     *
     * @param userId      userId
     * @param maxSendTime maxSendTime
     */
    @Transactional
    public List<Message> messageList(Integer userId, Date maxSendTime) {

        boolean isNeedSetReadStatus = (maxSendTime == null);

        if (maxSendTime == null) {
            maxSendTime = new Date();
        }

        List<Message> list = this.mapper.messageList(userId, maxSendTime);
        if (isNeedSetReadStatus) {
            this.mapper.setReadByUserId(userId);
        }
        return list;
    }
}
