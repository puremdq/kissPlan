package com.aojiaoo.core.handler;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.RequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.net.URI;
import java.security.Principal;
import java.util.Map;

public class MyHandsHandler extends DefaultHandshakeHandler {


    public MyHandsHandler() {
    }

    public MyHandsHandler(RequestUpgradeStrategy requestUpgradeStrategy) {
        super(requestUpgradeStrategy);
    }

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        URI uri = request.getURI();
        String id = null;
        if (uri != null) {
            String query = uri.getPath();
            String[] pairs = query.split("/");
            id = pairs[3];
        }
        MyPrincipal principal = new MyPrincipal();
        principal.setName(id);
        System.out.println(id);
        return principal;
    }

    class MyPrincipal implements Principal {
        private String name;

        @Override
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MyPrincipal that = (MyPrincipal) o;
            return name != null ? name.equals(that.name) : that.name == null;

        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }
}
