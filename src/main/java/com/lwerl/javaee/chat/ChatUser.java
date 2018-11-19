package com.lwerl.javaee.chat;

import com.lwerl.javaee.cdi.CDIEndpointConfigurator;
import lombok.AccessLevel;
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
public class ChatUser {

    private String login;
    private Session session;

    @Getter(AccessLevel.PRIVATE)
    private final ChatService service;

    @Inject
    public ChatUser(ChatService service) {
        this.service = service;
    }


    @OnOpen()
    public void onConnect(Session session, @PathParam("name") String login) {
        this.session = session;
        this.login = login;
        service.addUser(this);
    }

    @OnClose
    @OnError
    public void onClose() {
        service.removeUser(this);
    }

    @OnMessage
    public void onMessage(String msg) {
        service.broadcast(msg, this);
    }
}
