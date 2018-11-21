package com.lwerl.javaee.chat;

import com.lwerl.javaee.cdi.CDIEndpointConfigurator;
import lombok.Getter;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Getter
@ServerEndpoint(
        value = "/chat/connect/{name}",
        configurator = CDIEndpointConfigurator.class,
        encoders = MessageJsonEncoder.class
)
public class ChatEndPoint {

    private final ChatService service;
    private ChatUser user;

    @Inject
    public ChatEndPoint(ChatService service) {
        this.service = service;
    }

    @OnOpen()
    public void onConnect(Session session, @PathParam("name") String login) {
        this.user = new ChatUser(login, session);
        service.addUser(user);
    }

    @OnClose
    @OnError
    public void onClose() {
        service.removeUser(user);
    }

    @OnMessage
    public void onMessage(String msg) {
        service.broadcast(msg, user);
    }
}
