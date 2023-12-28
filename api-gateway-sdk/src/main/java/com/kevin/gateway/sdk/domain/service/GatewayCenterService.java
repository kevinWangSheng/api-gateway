package com.kevin.gateway.sdk.domain.service;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.kevin.gateway.sdk.GatewayException;
import com.kevin.gateway.sdk.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2023-12-29-0:38
 */
public class GatewayCenterService {
    private static final Logger logger = LoggerFactory.getLogger(GatewayCenterService.class);
    private String systemIdStr = "systemId";

    private String systemNameStr = "systemName";
    private String systemTypeStr = "systemType";
    private String systemRegistryStr = "systemRegistry";
    private String interfaceIdStr = "interfaceId";
    private String interfaceNameStr = "interfaceName";
    private String interfaceVersionStr = "interfaceVersion";

    private String methodIdStr = "methodId";
    private String methodNameStr = "methodName";
    private String paramterTypesStr = "parameterType";
    private String uriStr = "uri";
    private String httpCommandTypeStr = "httpCommandType";
    private String authStr = "auth";

    /**
     * 注册应用接口
     * @param address 地址
     * @param systemId 系统id
     * @param systemName 系统名称
     * @param systemType 系统类型
     * @param systemRegistry 系统注册地址
     */
    public void doRegisterApplication(String address,String systemId,String systemName,String systemType,String systemRegistry){
        Map<String,Object> paramter = new HashMap<>();
        paramter.put(systemIdStr,systemId);
        paramter.put(systemNameStr,systemName);
        paramter.put(systemTypeStr,systemType);
        paramter.put(systemRegistryStr,systemRegistry);

        String resultStr;

        try {
            resultStr = HttpUtil.post(address+"/wg/admin/register/registerApplication",paramter,550);
        } catch (Exception e) {
            logger.error("应用服务注册异常，链接资源不可用：{}",address+"/wg/admin/register/registerApplication");
            throw e;
        }
        Result<Boolean> result = JSON.parseObject(resultStr, new TypeReference<Result<Boolean>>() {
        });
        logger.info("应用服务注册，systemId:{},systemName:{},结果：{}",systemId,systemName,result);
        if(!"0000".equals(result.getCode()) || !"0003".equals(result.getCode())){
            throw new GatewayException("向网关注册服务异常,"+systemIdStr+":["+systemId+"],"+systemNameStr+":["+systemName+"]");
        }

    }

    /**
     * 注册应用接口
     * @param address 地址
     * @param systemId 系统id
     * @param interfaceId 接口id
     * @param interfaceName 接口名称
     * @param interfaceVersion 接口版本
     */
    public void doRegisterApplicationInterface(String address,String systemId,String interfaceId,String interfaceName,String interfaceVersion){
        Map<String,Object> paramter = new HashMap<>();
        paramter.put(systemIdStr,systemId);
        paramter.put(interfaceIdStr,interfaceId);
        paramter.put(interfaceNameStr,interfaceName);
        paramter.put(interfaceNameStr,interfaceVersion);

        String resultStr;

        try {
            resultStr = HttpUtil.post(address+"/wg/admin/register/registerApplicationInterface",paramter,550);
        } catch (Exception e) {
            logger.error("应用服务注册异常，链接资源不可用：{}",address+"/wg/admin/register/registerApplicationInterface");
            throw e;
        }
        Result<Boolean> result = JSON.parseObject(resultStr,new TypeReference<Result<Boolean>>(){});
        logger.info("应用服务注册信息：systemId:{},interfaceId:{},interfaceName:{},interfaceVersion:{},注册结果：{}",systemId,interfaceId,interfaceName,interfaceVersion,result);
        if(!"0000".equals(result.getCode()) || !"0003".equals(result.getCode())){
            throw new GatewayException("向网关注册服务异常，"+systemNameStr+"["+systemId+"],"+interfaceIdStr+"["+interfaceId+"],"+interfaceNameStr+"["+interfaceName+"],"+interfaceVersionStr+"["+interfaceVersion+"]");
        }
    }

    /**
     * 注册接口方法
     * @param address 地址
     * @param systemId 系统id
     * @param interfaceId 接口id
     * @param methodId 方法id
     * @param methodName 方法名称
     * @param paramterType 参数类型
     * @param uri 请求路径
     * @param httpCommandType 请求类型
     * @param auth 是否需要鉴权
     */
    public void doRegisterApplicationInterfaceMethod(String address,String systemId,String interfaceId,String methodId,String methodName,String paramterType,String uri,String httpCommandType,Integer auth){
        Map<String,Object> paramter = new HashMap<>();
        paramter.put(systemIdStr,systemId);
        paramter.put(interfaceIdStr,interfaceId);
        paramter.put(methodIdStr,methodId);
        paramter.put(methodNameStr,methodName);
        paramter.put(paramterTypesStr, paramterType);
        paramter.put(uriStr, uri);
        paramter.put(httpCommandTypeStr, httpCommandType);
        paramter.put(authStr, auth);

        String resultStr;

        try {
            resultStr = HttpUtil.post(address+"/wg/admin/register/registerApplicationInterface",paramter,550);
        } catch (Exception e) {
            logger.error("应用服务注册异常，链接资源不可用：{}",address+"/wg/admin/register/registerApplicationInterface");
            throw e;
        }
        Result<Boolean> result = JSON.parseObject(resultStr,new TypeReference<Result<Boolean>>(){});
        logger.info("应用服务注册信息：systemId:{},interfaceId:{},methodId:{},methodName:{},注册结果：{}",systemId,interfaceId,methodId,methodName,result);
        if(!"0000".equals(result.getCode()) || !"0003".equals(result.getCode())){
            throw new GatewayException("向网关注册服务异常，"+systemNameStr+"["+systemId+"],"+interfaceIdStr+"["+interfaceId+"],"+methodIdStr+"["+methodId+"],"+methodNameStr+"["+methodName+"]");
        }
    }


    /**
     * 注册事件
     * @param address 地址
     * @param systemId 系统id
     */
    public void doRegisterEvent(String address,String systemId){
        Map<String,Object> paramter = new HashMap<>();
        paramter.put(systemIdStr,systemId);

        String resultStr;

        try {
            resultStr = HttpUtil.post(address+"/wg/admin/register/registerApplicationInterface",paramter,550);
        } catch (Exception e) {
            logger.error("应用服务注册异常，链接资源不可用：{}",address+"/wg/admin/register/registerApplicationInterface");
            throw e;
        }
        Result<Boolean> result = JSON.parseObject(resultStr,new TypeReference<Result<Boolean>>(){});
        logger.info("应用服务注册信息：systemId:{},注册结果：{}",systemId,result);
        if(!"0000".equals(result.getCode()) || !"0003".equals(result.getCode())){
            throw new GatewayException("向网关注册服务异常，"+systemNameStr+"["+systemId+"],");
        }
    }

}
