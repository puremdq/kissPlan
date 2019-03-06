package com.aojiaoo.core.socket;

import com.aojiaoo.core.common.GlobalProperties;
import com.aojiaoo.utils.CacheUtils;
import com.aojiaoo.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class SessionAuthHandshakeInterceptor implements HandshakeInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            String token = serverRequest.getServletRequest().getParameter(GlobalProperties.TOKEN_NAME);
            if (StringUtils.isBlank(token) || CacheUtils.get(GlobalProperties.TOKEN_USERID_CACHE_NAME, token) == null) {
                logger.warn("websocket权限拒绝");
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler
            wsHandler, Exception exception) {
        System.out.println("afterHandshake");
    }


}