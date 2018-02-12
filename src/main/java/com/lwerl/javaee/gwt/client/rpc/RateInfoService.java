package com.lwerl.javaee.gwt.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Created by lWeRl on 04.02.2018.
 */
@RemoteServiceRelativePath("rateinfo")
public interface RateInfoService extends RemoteService{

    public String getHtml();

}
