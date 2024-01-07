package com.kevin.gateway.interfaces;

import com.kevin.gateway.application.ILoadBalancingService;
import com.kevin.gateway.domain.docker.model.aggregates.NginxConfig;
import com.kevin.gateway.domain.docker.model.vo.LocationVO;
import com.kevin.gateway.domain.docker.model.vo.UpstreamVO;
import com.kevin.gateway.infrustructs.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wang
 * @create 2024-01-07-13:31
 */
@RestController
@RequestMapping("/wg/admin/load")
public class LoadBalanceManager {
    private static final Logger logger = LoggerFactory.getLogger(LoadBalanceManager.class);

    @Resource
    private ILoadBalancingService loadBalancingService;

    @PostMapping("copy")
    public void copy(){
        ProcessBuilder pb = new ProcessBuilder("docker", "cp", "/nginx.conf", "host:/Users/fuzhengwei/1024/KnowledgePlanet/api-gateway/api-gateway-center/doc/data/nginx/nginx.conf");
        try {
            pb.start();
        } catch (Exception e) {
            logger.error("copy error", e);
        }
    }

    @PostMapping("updateNginxConfig")
    public void updateNginxConfig(){
        try {
            List<UpstreamVO> upstreamList = new ArrayList<>();
            upstreamList.add(new UpstreamVO("api01", "least_conn;", Arrays.asList("192.168.1.102:9001;", "192.168.1.102:9002;")));
            upstreamList.add(new UpstreamVO("api02", "least_conn;", Arrays.asList("192.168.1.102:9003;")));

            List<LocationVO> locationList = new ArrayList<>();
            locationList.add(new LocationVO("/api01/", "http://api01;"));
            locationList.add(new LocationVO("/api02/", "http://api02;"));
            NginxConfig nginxConfig = new NginxConfig(upstreamList,locationList);
            loadBalancingService.updateNginxConfig(nginxConfig);
        } catch (Exception e) {
            logger.error("updateNginxConfig error", e);
        }
    }
}
