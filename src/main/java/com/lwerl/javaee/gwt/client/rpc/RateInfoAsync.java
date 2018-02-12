package com.lwerl.javaee.gwt.client.rpc;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by lWeRl on 04.02.2018.
 */
public class RateInfoAsync implements AsyncCallback<String> {

    private Element element;

    public RateInfoAsync(Element element) {
        this.element = element;
    }

    @Override
    public void onFailure(Throwable caught) {

    }

    @Override
    public void onSuccess(String result) {
        element.setInnerHTML(result);
    }
}
