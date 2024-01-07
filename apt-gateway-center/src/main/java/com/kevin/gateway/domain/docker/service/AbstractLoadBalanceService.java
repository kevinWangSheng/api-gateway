package com.kevin.gateway.domain.docker.service;

import com.kevin.gateway.application.ILoadBalancingService;
import com.kevin.gateway.domain.docker.model.aggregates.NginxConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author wang
 * @create 2024-01-07-13:36
 */
public abstract class AbstractLoadBalanceService implements ILoadBalancingService {
    static final Logger logger = LoggerFactory.getLogger(AbstractLoadBalanceService.class);
    @Override
    public void updateNginxConfig(NginxConfig nginxConfig) throws IOException, InterruptedException {
        String nginxConfigFilePath = createNginxConfigFile(nginxConfig);
        logger.info("创建nginx配置文件，路径：{}", nginxConfigFilePath);
        reflushNginxConfig(nginxConfig.getNginxName());
        logger.info("刷新nginx配置文件，nginxName：{}", nginxConfig.getNginxName());
    }

    protected abstract String createNginxConfigFile(NginxConfig nginxConfig) throws IOException;

    protected abstract void copyDockerFile(String applicationName, String containerFilePath, String localNginxPath) throws InterruptedException, IOException;

    protected abstract void reflushNginxConfig(String nginxName) throws InterruptedException, IOException;
}
