package com.aojiaoo.config.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@com.aojiaoo.core.socket.EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/receive");//(服务器)i发送前缀 （客户端）接受前缀
        config.setApplicationDestinationPrefixes("/send");   //(服务器)接受前缀  （客户端）发送前缀
        config.setUserDestinationPrefix("/secured/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/msg").
                addInterceptors(new com.aojiaoo.core.socket.SessionAuthHandshakeInterceptor()).
                setHandshakeHandler(new com.aojiaoo.core.socket.HandsHandler()).setAllowedOrigins("*").withSockJS();
    }


    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new com.aojiaoo.core.socket.ChannelInterceptor());
    }

}