package com.lwerl.javaee.chat;

import lombok.NonNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Singleton;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Singleton
public class ChatService {

    private Set<ChatUser> userSet = new CopyOnWriteArraySet<>();

    void addUser(@Nonnull @NonNull ChatUser user) {
        userSet.add(user);
        broadcast(String.format("User %s join room.", user.getLogin()), null);
    }

    void removeUser(@Nonnull @NonNull ChatUser user) {
        userSet.remove(user);
        broadcast(String.format("User %s leave room.", user.getLogin()), null);
    }

    void broadcast(@Nonnull @NonNull String msg, @Nullable ChatUser from) {
        String text;

        if (from != null) {
            text = String.format("%s: %s", from.getLogin(), msg);
        } else {
            text = msg;
        }

        userSet.forEach(user ->
                user.getSession().getAsyncRemote().sendObject(new MessageJsonEncoder.Message(text), result -> {
                    if (!result.isOK()) {
                        removeUser(user);
                    }
                })
        );

    }

}
