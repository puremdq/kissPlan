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
            if (StringUtils.isBlank(token)) {
                logger.error("websocket权限拒绝");
                return false;
            }

//            CacheUtils.get()

//        SecurityUtils.getSubject().runAs();

//        HttpSession session = getSession(request);
//        if(session==null || session.getAttribute("user")==null){
//            logger.error("websocket权限拒绝");
//            return false;
//        }
//        attributes.put("user",session.getAttribute("user"));
        }

        return true;

    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler
            wsHandler, Exception exception) {

        if (request instanceof ServletServerHttpRequest) {
            System.out.println("后面属于ServletServerHttpRequest  ");
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            System.out.println(serverRequest.getServletRequest().getParameter("kiss_plan_token"));
        }
        System.out.println("afterHandshake");
    }

    // 参考 HttpSessionHandshakeInterceptor
//    private HttpSession getSession(ServerHttpRequest request) {
//        if (request instanceof ServletServerHttpRequest) {
//            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
//            return serverRequest.getServletRequest().getSession(false);
//        }
//        return null;
//    }
}