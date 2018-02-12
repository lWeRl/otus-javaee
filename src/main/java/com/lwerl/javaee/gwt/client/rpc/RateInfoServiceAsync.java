package com.lwerl.javaee.gwt.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

public interface RateInfoServiceAsync {
    void getHtml(AsyncCallback<String> async);
}
