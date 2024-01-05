package com.kevin.gateway.application;

import com.kevin.gateway.config.GatewayServiceProperties;
import com.kevin.gateway.domain.model.aggregates.ApplicationSystemRichInfo;
import com.kevin.gateway.domain.model.vo.ApplicationInterfaceMethodVO;
import com.kevin.gateway.domain.model.vo.ApplicationInterfaceVO;
import com.kevin.gateway.domain.model.vo.ApplicationSystemVO;
import com.kevin.gateway.domain.service.GatewayCenterService;
import io.netty.channel.Channel;
import org.kevin.gateway.mapping.HttpCommandType;
import org.kevin.gateway.mapping.HttpStatement;
import org.kevin.gateway.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-02-21:33
 */
public class GatewayApplication implements ApplicationContextAware, ApplicationListener<ContextClosedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(GatewayApplication.class);
    private GatewayServiceProperties properties;

    private GatewayCenterService gatewayService;

    private Configuration configuration;

    private Channel gatewaySocketServerChannel;

    public GatewayApplication(GatewayServiceProperties properties, GatewayCenterService gatewayService, Configuration configuration, Channel gatewaySocketServerChannel) {
        this.properties = properties;
        this.gatewayService = gatewayService;
        this.configuration = configuration;
        this.gatewaySocketServerChannel = gatewaySocketServerChannel;
    }



    public void addMappers(String systemId){
        ApplicationSystemRichInfo applicationSystemRichInfo = gatewayService.pullApplicationSystemRichInfo(properties.getAddress(), properties.getGatewayId(), systemId);
        List<ApplicationSystemVO> applicationSystemVOList = applicationSystemRichInfo.getApplicationSystemVOList();
        for(ApplicationSystemVO systemVO:applicationSystemVOList){
            List<ApplicationInterfaceVO> interfaceList = systemVO.getInterfaceList();

            for(ApplicationInterfaceVO interfaceVO:interfaceList){
                List<ApplicationInterfaceMethodVO> methodList = interfaceVO.getMethodList();
                configuration.registryConfig(systemVO.getSystemId(),systemVO.getSystemRegistry(),interfaceVO.getInterfaceId(),interfaceVO.getInterfaceVersion());
                for(ApplicationInterfaceMethodVO methodVO:methodList){
                    configuration.addMapper(new HttpStatement(methodVO.getUri(),  interfaceVO.getInterfaceName(),methodVO.getMethodName(),
                            systemVO.getSystemId(), HttpCommandType.getType(methodVO.getHttpCommandType()),methodVO.getParameterTypes(),methodVO.getAuth()==1));
                    logger.info("网关服务注册映射 系统：{} 接口：{} 方法：{}", systemVO.getSystemId(), interfaceVO.getInterfaceId(), methodVO.getMethodId());
                }

            }
        }
    }


    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        try {
            if (gatewaySocketServerChannel.isActive()) {
                logger.info("应用容器关闭，Api网关服务关闭。localAddress：{}", gatewaySocketServerChannel.localAddress());
                gatewaySocketServerChannel.close();
            }
        } catch (Exception e) {
            logger.error("应用容器关闭，Api网关服务关闭失败", e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            gatewayService.doRegister(properties.getAddress(),
                    properties.getGroupId(),
                    properties.getGatewayId(),
                    properties.getGatewayName(),
                    properties.getGatewayAddress());
            addMappers("");
        } catch (Exception e) {
            logger.error("网关服务启动失败，停止服务。{}", e.getMessage(), e);
            throw e;
        }
    }

    public void receiveMessage(Object message) {
        logger.info("【事件通知】接收注册中心推送消息 message：{}", message);
        addMappers(message.toString().substring(1, message.toString().length() - 1));
    }
}
