package com.kevin.gateway.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.kevin.gateway.GatewayException;
import com.kevin.gateway.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2024-01-02-21:33
 */
public class RegistryGatewayService {
    private static final Logger logger = LoggerFactory.getLogger(RegistryGatewayService.class);

    public void doRegister(String address, String groupId, String gatewayId, String gatewayName, String gatewayAddress) {
        Map<String,Object> paramMap = new HashMap<>();

        try {
            paramMap.put("groupId",groupId);
            paramMap.put("gatewayId",gatewayId);
            paramMap.put("gatewayName",gatewayName);
            paramMap.put("gatewayAddress",gatewayAddress);
            String resultStr = HttpUtil.post(address, paramMap, 350);
            Result result = JSON.parseObject(resultStr, Result.class);
            logger.info("doRegister 返回结果 result:{},address:{},groupId:{},gatewayId:{},gatewayName:{},gatewayAddress:{}",result,address, groupId, gatewayId, gatewayName, gatewayAddress);
        } catch (Exception e) {
            throw new GatewayException("网关服务注册异常 [gatewayId：" + gatewayId + "] 、[gatewayAddress：" + gatewayAddress + "]");
        }
    }
}
