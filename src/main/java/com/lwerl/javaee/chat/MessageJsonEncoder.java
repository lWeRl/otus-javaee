package com.lwerl.javaee.chat;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageJsonEncoder implements Encoder.Text<MessageJsonEncoder.Message> {



    @Override
    public String encode(Message object) throws EncodeException {
        return new Gson().toJson(object);
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private String message;
    }
}
