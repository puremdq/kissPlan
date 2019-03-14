package com.aojiaoo.core.socket;

import com.aojiaoo.core.common.GlobalProperties;
import com.aojiaoo.utils.CacheUtils;
import com.aojiaoo.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

import java.security.Principal;

public class ChannelInterceptor implements org.springframework.messaging.support.ChannelInterceptor {

    private final Logger logger = LoggerFactory.getLogger(ChannelInterceptor.class);


    @Override
    @Nullable
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        return message;
    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {


        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        //用户已经断开连接
        if (StompCommand.DISCONNECT.equals(command)) {
            String userId = "";
            Principal principal = accessor.getUser();
            if (principal != null && StringUtils.isNoneBlank(principal.getName())) {
                userId = principal.getName();
            } /*else {
                user = accessor.getSessionId();
            }*/

            CacheUtils.remove(GlobalProperties.CONNECTED_SOCKET_USER_CACHE_NAME, userId);
            logger.debug("用户{}断开连接，已从在线缓存中移除", userId);
        }
    }

}