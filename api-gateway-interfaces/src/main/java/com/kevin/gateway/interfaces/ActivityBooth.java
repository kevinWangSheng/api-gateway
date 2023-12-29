package com.kevin.gateway.interfaces;

import com.alibaba.fastjson.JSON;
import com.kevin.gateway.rpc.IActivityBooth;
import com.kevin.gateway.rpc.dto.XReq;
import com.kevin.gateway.sdk.annotation.ApiProducerClazz;
import com.kevin.gateway.sdk.annotation.ApiProducerMethod;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author wang
 * @create 2023-12-28-23:21
 */
@Service(version = "1.0.0")
@ApiProducerClazz(interfaceName = "服务活动测试",interfaceVersion = "1.0.0")
public class ActivityBooth implements IActivityBooth {
    @Override
    @ApiProducerMethod(methodName = "探活方法", uri = "/wg/activity/sayHi", httpCommandType = "GET", auth = 0)
    public String sayHi(String str) {
        return "hi"+str;
    }

    @Override
    @ApiProducerMethod(methodName = "插入方法",uri = "/wg/activity/insert",httpCommandType = "POST",auth = 1)
    public String insert(XReq req) {
        return "insert"+ JSON.toJSONString(req);
    }

    @Override
    @ApiProducerMethod(methodName = "测试方法",uri = "/wg/activity/test",httpCommandType = "POST",auth = 0)
    public String test(String str, XReq req) {
        return "test"+str+JSON.toJSONString(req);
    }
}
