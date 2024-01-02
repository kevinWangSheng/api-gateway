package com.kevin.gateway.infrustructs.dao;

import com.kevin.gateway.infrustructs.po.GatewayServer;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-02-16:05
 */
public interface IGatewayServerDao {
    List<GatewayServer> queryGatewayServerList();
}
