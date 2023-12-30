package org.kevin.gateway.datasource.connection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.kevin.gateway.datasource.Connection;

import java.io.IOException;

/**
 * @author wang
 * @create 2023-12-30-14:14
 */
public class HttpConnection implements Connection {

    private HttpClient httpClient;

    private PostMethod postMethod;

    public HttpConnection(String uri) {
        this.httpClient = new HttpClient();
        this.postMethod = new PostMethod(uri);
        postMethod.addParameter("accept","*/*");
        postMethod.addParameter("connection","Keep-Alive");
        postMethod.addParameter("content","application/json;charset=GBK");
        postMethod.addParameter("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
    }

    @Override
    public Object execute(String method, String[] paramterTypes, String[] paramterNames, Object[] args) {
        String res = null;
        try {
            int code = httpClient.executeMethod(postMethod);
            if(200 == code){
                res = postMethod.getResponseBodyAsString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return  res;
    }
}
