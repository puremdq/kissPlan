/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aojiaoo.core.socket;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SubProtocolHandler;

/**
 * An implementation of {@link WebSocketHandler} that delegates incoming WebSocket
 * messages to a {@link SubProtocolHandler} along with a {@link MessageChannel} to which
 * the sub-protocol handler can send messages from WebSocket clients to the application.
 *
 * <p>Also an implementation of {@link MessageHandler} that finds the WebSocket session
 * associated with the {@link Message} and passes it, along with the message, to the
 * sub-protocol handler to send messages from the application back to the client.
 *
 * @author Rossen Stoyanchev
 * @author Juergen Hoeller
 * @author Andy Wilkinson
 * @author Artem Bilan
 * @since 4.0
 */
public class SubProtocolWebSocketHandler
        extends org.springframework.web.socket.messaging.SubProtocolWebSocketHandler {


    /**
     * Create a new {@code SubProtocolWebSocketHandler} for the given inbound and outbound channels.
     *
     * @param clientInboundChannel  the inbound {@code MessageChannel}
     * @param clientOutboundChannel the outbound {@code MessageChannel}
     */
    public SubProtocolWebSocketHandler(MessageChannel clientInboundChannel, SubscribableChannel clientOutboundChannel) {
        super(clientInboundChannel, clientOutboundChannel);
    }


    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("start seassuion");
        System.out.println(session.getAttributes());
        System.out.println("end seassuion");

        super.afterConnectionEstablished(session);

    }

    /**
     * Invoked when a new WebSocket message arrives.
     *
     * @throws Exception this method can handle or propagate exceptions; see class-level
     *                   Javadoc for details.
     */
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("处理消息:" + session.getId());
        System.out.println(message.toString());
        super.handleMessage(session, message);
    }

    /**
     * Handle an error from the underlying WebSocket message transport.
     *
     * @throws Exception this method can handle or propagate exceptions; see class-level
     *                   Javadoc for details.
     */
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    /**
     * Invoked after the WebSocket connection has been closed by either side, or after a
     * transport error has occurred. Although the session may technically still be open,
     * depending on the underlying implementation, sending messages at this point is
     * discouraged and most likely will not succeed.
     *
     * @throws Exception this method can handle or propagate exceptions; see class-level
     *                   Javadoc for details.
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("关闭连接:" + session.getId());
        super.afterConnectionClosed(session, closeStatus);
    }
}
