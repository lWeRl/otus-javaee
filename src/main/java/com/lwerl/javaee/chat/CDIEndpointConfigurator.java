package com.lwerl.javaee.chat;

import javax.enterprise.inject.spi.CDI;
import javax.websocket.server.ServerEndpointConfig;

public class CDIEndpointConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        return CDI.current().select(endpointClass).get();
    }
}
