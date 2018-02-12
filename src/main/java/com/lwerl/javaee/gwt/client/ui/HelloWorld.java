package com.lwerl.javaee.gwt.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

/**
 * Created by lWeRl on 04.02.2018.
 */
public class HelloWorld {


    interface HelloWorldUiBinder extends UiBinder<DivElement, HelloWorld> {
    }

    private static HelloWorldUiBinder uiBinder = GWT.create(HelloWorldUiBinder.class);

    @UiField
    public SpanElement name;

    public void setName(String name) {
        this.name.setInnerText(name);
    }

    private final DivElement rootElement;

    public DivElement getRootElement() {
        return rootElement;
    }

    public HelloWorld() {
        rootElement = uiBinder.createAndBindUi(this);
    }


}