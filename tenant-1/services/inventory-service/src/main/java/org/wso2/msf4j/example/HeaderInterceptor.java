package org.wso2.msf4j.example;

import org.wso2.msf4j.Interceptor;
import org.wso2.msf4j.Request;
import org.wso2.msf4j.Response;
import org.wso2.msf4j.ServiceMethodInfo;

/**
 * Created by imesh on 10/12/16.
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public boolean preCall(Request request, Response response, ServiceMethodInfo serviceMethodInfo) throws Exception {
        System.out.println("---> HTTP Headers:");
        for(String key : request.getHeaders().keySet()) {
            System.out.println(key + ": " + request.getHeader(key));
        }
        return true;
    }

    @Override
    public void postCall(Request request, int i, ServiceMethodInfo serviceMethodInfo) throws Exception {
        System.out.println("---> Response message sent");
    }
}
