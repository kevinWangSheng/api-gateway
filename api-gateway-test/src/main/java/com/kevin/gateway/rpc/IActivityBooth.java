package com.kevin.gateway.rpc;

import com.kevin.gateway.rpc.dto.XReq;

/**
 * @author wang
 * @create 2023-12-28-23:15
 */
public interface IActivityBooth {
    String sayHi(String str);

    String insert(XReq req);

    String test(String str, XReq req);
}
