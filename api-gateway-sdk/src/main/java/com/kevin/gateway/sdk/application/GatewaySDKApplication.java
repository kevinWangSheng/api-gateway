package com.kevin.gateway.sdk.application;

import com.alibaba.fastjson.JSON;
import com.kevin.gateway.sdk.GatewayException;
import com.kevin.gateway.sdk.annotation.ApiProducerClazz;
import com.kevin.gateway.sdk.annotation.ApiProducerMethod;
import com.kevin.gateway.sdk.config.GatewaySDKConfigProperties;
import com.kevin.gateway.sdk.domain.service.GatewayCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;

/**
 * @author wang
 * @create 2023-12-29-1:23
 */
public class GatewaySDKApplication implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(GatewaySDKApplication.class);
    private GatewaySDKConfigProperties properties;

    private GatewayCenterService service;

    public GatewaySDKApplication(GatewaySDKConfigProperties properties, GatewayCenterService service) {
        this.properties = properties;
        this.service = service;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ApiProducerClazz apiProducerClazz = bean.getClass().getAnnotation(ApiProducerClazz.class);
        if (null == apiProducerClazz) return bean;
        // 1. 系统信息
        logger.info("\n应用注册：系统信息 \nsystemId: {} \nsystemName: {} \nsystemType: {} \nsystemRegistry: {}", properties.getSystemId(), properties.getSystemName(), "RPC", properties.getSystemRegistry());
        service.doRegisterApplication(properties.getAddress(), properties.getSystemId(), properties.getSystemName(), "RPC", properties.getSystemRegistry());

        // 2. 接口信息
        Class<?>[] interfaces = bean.getClass().getInterfaces();
        if (interfaces.length != 1) {
            throw new GatewayException(bean.getClass().getName() + "interfaces not one this is " + JSON.toJSONString(interfaces));
        }
        String interfaceId = interfaces[0].getName();
        logger.info("\n应用注册：接口信息 \nsystemId: {} \ninterfaceId: {} \ninterfaceName: {} \ninterfaceVersion: {}", properties.getSystemId(), interfaceId, apiProducerClazz.interfaceName(), apiProducerClazz.interfaceVersion());
        service.doRegisterApplicationInterface(properties.getAddress(),
                properties.getSystemId(),
                interfaceId,
                apiProducerClazz.interfaceName(),
                apiProducerClazz.interfaceVersion());

        // 3. 方法信息
        Method[] methods = bean.getClass().getMethods();
        for (Method method : methods) {
            ApiProducerMethod apiProducerMethod = method.getAnnotation(ApiProducerMethod.class);
            if (apiProducerMethod == null) continue;
            // 解析参数
            Class<?>[] parameterTypes = method.getParameterTypes();
            StringBuilder parameters = new StringBuilder();
            for (Class<?> clazz : parameterTypes) {
                parameters.append(clazz.getName()).append(",");
            }
            String parameterType = parameters.toString().substring(0, parameters.toString().lastIndexOf(","));
            logger.info("\n应用注册：方法信息 \nsystemId: {} \ninterfaceId: {} \nmethodId: {} \nmethodName: {} \nparameterType: {} \nuri: {} \nhttpCommandType: {} \nauth: {}",
                    properties.getSystemId(),
                    interfaceId,
                    method.getName(),
                    apiProducerMethod.methodName(),
                    parameterType,
                    apiProducerMethod.uri(),
                    apiProducerMethod.httpCommandType(),
                    apiProducerMethod.auth());
            service.doRegisterApplicationInterfaceMethod(properties.getAddress(),
                    properties.getSystemId(),
                    interfaceId,
                    method.getName(),
                    apiProducerMethod.methodName(),
                    parameterType,
                    apiProducerMethod.uri(),
                    apiProducerMethod.httpCommandType(),
                    apiProducerMethod.auth());
        }

        // 注册完成，执行事件通知
        service.doRegisterEvent(properties.getAddress(), properties.getSystemId());

        return bean;
    }
}
