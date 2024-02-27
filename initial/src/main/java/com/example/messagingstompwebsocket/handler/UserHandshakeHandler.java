package com.example.messagingstompwebsocket.handler;

import com.sun.security.auth.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.http.server.ServerHttpRequest;
import  org.springframework.web.socket.WebSocketHandler;
import java.util.Map;

import java.security.Principal;
import java.util.UUID;

public class UserHandshakeHandler extends DefaultHandshakeHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(UserHandshakeHandler.class);

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        String randomId = UUID.randomUUID().toString();
        LOGGER.info("Connection from new user with random id: " + randomId);
        return new UserPrincipal(randomId);
    }
}
