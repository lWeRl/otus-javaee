package com.lwerl.javaee.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.websocket.Session;

@AllArgsConstructor
@Data
public class ChatUser {

    private final String login;
    private final Session session;

}
