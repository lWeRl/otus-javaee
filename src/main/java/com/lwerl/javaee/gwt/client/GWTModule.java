package com.lwerl.javaee.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import com.lwerl.javaee.gwt.client.helper.MainContent;
import com.lwerl.javaee.gwt.client.rpc.RateInfoAsync;
import com.lwerl.javaee.gwt.client.rpc.RateInfoService;
import com.lwerl.javaee.gwt.client.rpc.RateInfoServiceAsync;

/**
 * Created by lWeRl on 03.02.2018.
 */
public class GWTModule implements EntryPoint {



    public void onModuleLoad() {
        final RootPanel navigation = RootPanel.get("nav");
        final RootPanel leftPanel = RootPanel.get("left-side");
        final RootPanel rightPanel = RootPanel.get("right-side");

        RateInfoServiceAsync rateInfoService = GWT.create(RateInfoService.class);
        RateInfoAsync rateInfoAsync =  new RateInfoAsync(rightPanel.getElement());
        rateInfoService.getHtml(rateInfoAsync);

        new Timer() {
            @Override
            public void run() {
                rateInfoService.getHtml(rateInfoAsync);
            }
        }.scheduleRepeating(1000 * 60 * 5);

        navigation.getElement().appendChild(MainContent.createNavElement("Главная", event -> {
            leftPanel.getElement().setInnerHTML("");
            leftPanel.getElement().setInnerText("I'm main");
        }));
        navigation.getElement().appendChild(MainContent.createNavElement("Логин", event -> {
            leftPanel.getElement().setInnerHTML("");
        }));
        navigation.getElement().appendChild(MainContent.createNavElement("Новости", event -> {
            leftPanel.getElement().setInnerHTML("");
        }));
        navigation.getElement().appendChild(MainContent.createNavElement("Скрипт", event -> {
            leftPanel.getElement().setInnerHTML("");
        }));
        navigation.getElement().appendChild(MainContent.createNavElement("Работники", event -> {
            leftPanel.getElement().setInnerHTML("");
        }));
    }
}
