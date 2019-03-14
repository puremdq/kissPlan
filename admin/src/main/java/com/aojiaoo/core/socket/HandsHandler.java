package com.aojiaoo.core.socket;

import com.aojiaoo.core.common.GlobalProperties;
import com.aojiaoo.utils.CacheUtils;
import com.aojiaoo.utils.StringUtils;
import com.aojiaoo.utils.UserUtil;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.RequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

public class HandsHandler extends DefaultHandshakeHandler {

    public HandsHandler() {
    }

    public HandsHandler(RequestUpgradeStrategy requestUpgradeStrategy) {
        super(requestUpgradeStrategy);
    }


    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
//        wsHandler.handleMessage();

        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            String token = serverRequest.getServletRequest().getParameter(GlobalProperties.TOKEN_NAME);
            return new WsPrincipal(StringUtils.toString(CacheUtils.get(GlobalProperties.TOKEN_USERID_CACHE_NAME, token)));
        }

        return null;
    }

    class WsPrincipal implements java.security.Principal {

        private String name;

        WsPrincipal() {
        }

        WsPrincipal(String name) {
            this.name = name;
        }


        @Override
        public String getName() {
            return this.name;
        }

    }

}
