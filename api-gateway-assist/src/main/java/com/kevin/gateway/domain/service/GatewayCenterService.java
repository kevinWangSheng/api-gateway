package com.kevin.gateway.domain.service;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.kevin.gateway.GatewayException;
import com.kevin.gateway.common.Result;
import com.kevin.gateway.domain.model.aggregates.ApplicationSystemRichInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2024-01-02-21:33
 */
public class GatewayCenterService {
    private static final Logger logger = LoggerFactory.getLogger(GatewayCenterService.class);

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

    public ApplicationSystemRichInfo pullApplicationSystemRichInfo(String address,String gatewayId,String systemId){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("gatewayId", gatewayId);
        paramMap.put("systemId", systemId);

        String resultStr;
        try {
            resultStr = HttpUtil.post(address + "/wg/admin/config/queryApplicationSystemRichInfo", paramMap, 1550);
        } catch (Exception e) {
            logger.error("网关服务拉取异常，链接资源不可用：{}", address + "/wg/admin/config/queryApplicationSystemRichInfo", e);
            throw e;
        }
        Result<ApplicationSystemRichInfo> result = JSON.parseObject(resultStr, new com.alibaba.fastjson.TypeReference<Result<ApplicationSystemRichInfo>>() {
        });
        logger.info("从网关中心拉取应用服务和接口的配置信息到本地完成注册。gatewayId：{}", gatewayId);
        if (!"0000".equals(result.getCode()))
            throw new GatewayException("从网关中心拉取应用服务和接口的配置信息到本地完成注册异常 [gatewayId：" + gatewayId + "]");
        return result.getData();
    }

    public Map<String,String> queryRedisConfig(String address){
        String resultStr = null;
        try {
            resultStr = HttpUtil.post(address+"/wg/admin/config/queryRedisConfig","",1550);
        } catch (Exception e) {
            logger.error("网关服务拉取异常，链接资源不可用：{}", address + "/wg/admin/config/queryRedisConfig", e);
        }
        Result<Map<String,String>> result = JSON.parseObject(resultStr, new TypeReference<Result<Map<String, String>>>() {
        });
        if("0000".equals(result.getCode())){
            throw new GatewayException("拉取服务配置信息出错,"+result);
        }
        logger.info("从网关拉取redis服务配置信息{}",result);
        return result.getData();
    }
}
