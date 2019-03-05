package com.aojiaoo.core.socket;

import com.aojiaoo.utils.UserUtil;
import org.springframework.http.server.ServerHttpRequest;
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
        return UserUtil.getPrincipal();
    }

}
