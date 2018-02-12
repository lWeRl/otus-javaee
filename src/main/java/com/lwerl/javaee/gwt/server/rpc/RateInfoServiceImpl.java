package com.lwerl.javaee.gwt.server.rpc;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.lwerl.javaee.cache.CurrencyRatesHolder;
import com.lwerl.javaee.gwt.client.rpc.RateInfoService;

import javax.inject.Inject;

/**
 * Created by lWeRl on 04.02.2018.
 */
public class RateInfoServiceImpl extends RemoteServiceServlet implements RateInfoService {

    @Inject
    private CurrencyRatesHolder holder;

    @Override
    public String getHtml() {
        return holder.getHtmlView();
    }
}
