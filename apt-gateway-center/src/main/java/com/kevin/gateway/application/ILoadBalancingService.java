package com.kevin.gateway.application;

import com.kevin.gateway.domain.docker.model.aggregates.NginxConfig;

import java.io.IOException;

/**
 * @author wang
 * @create 2024-01-07-13:24
 */
public interface ILoadBalancingService {
    void updateNginxConfig(NginxConfig nginxConfig) throws IOException, InterruptedException;
}
