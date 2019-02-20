package com.aojiaoo.config;

import com.aojiaoo.core.handler.MyHandsHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/receive");//(服务器)发送前缀 （客户端）接受前缀
        config.setApplicationDestinationPrefixes("/send");   //(服务器)接受前缀  （客户端）发送前缀
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/msg").setHandshakeHandler(new MyHandsHandler()).setAllowedOrigins("*").withSockJS();
    }

}