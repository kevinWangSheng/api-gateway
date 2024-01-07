package com.kevin.gateway.interfaces;

import com.kevin.gateway.application.IApiService;
import com.kevin.gateway.application.IConfigManageService;
import com.kevin.gateway.application.ILoadBalancingService;
import com.kevin.gateway.application.IMessageService;
import com.kevin.gateway.domain.docker.model.aggregates.NginxConfig;
import com.kevin.gateway.domain.docker.model.vo.LocationVO;
import com.kevin.gateway.domain.docker.model.vo.UpstreamVO;
import com.kevin.gateway.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import com.kevin.gateway.domain.manage.model.dto.*;
import com.kevin.gateway.domain.manage.model.vo.ApplicationSystemVO;
import com.kevin.gateway.domain.manage.model.vo.GatewayServerDetailVO;
import com.kevin.gateway.domain.manage.model.vo.GatewayServerVO;
import com.kevin.gateway.domain.operator.model.vo.*;
import com.kevin.gateway.infrustructs.common.ResponseCode;
import com.kevin.gateway.infrustructs.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author wang
 * @create 2024-01-01-0:41
 */
@CrossOrigin // 允许跨域
@RestController
@RequestMapping("/wg/admin/config")
public class GatewayConfigManageController {
    private static final Logger logger = LoggerFactory.getLogger(GatewayConfigManageController.class);
    @Resource
    private IApiService apiService;

    @Resource
    private IMessageService messageService;

    @Resource
    private IConfigManageService configManageService;

    @Resource
    private ILoadBalancingService loadBalancingService;

    @GetMapping("queryServerConfig")
    public Result<List<GatewayServerVO>> queryServerConfig() {
        try {
            logger.info("查询网关服务配置项信息");
            List<GatewayServerVO> gatewayServerVOList = configManageService.queryGatewayServerList();
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(),gatewayServerVOList);
        } catch (Exception e) {
            logger.error("查询网关服务配置项信息异常", e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }

    @PostMapping("registryGateway")
    public Result<Boolean> registryGatewayServerNode(String groupId, String gatewayId, String gatewayName, String gatewayAddress) {
        try {
            logger.info("注册网关服务节点 gatewayId：{} gatewayName：{} gatewayAddress：{}", gatewayId, gatewayName, gatewayAddress);
            boolean done = configManageService.registryGatewayServerNode(groupId, gatewayId, gatewayName, gatewayAddress);
            // 获取对应注册的网关的所有细节信息
            List<GatewayServerDetailVO> detailVOList = configManageService.queryGatewayServerDetailListByGatewayId(gatewayId);
            Map<String, List<GatewayServerDetailVO>> byGourpMap = detailVOList.stream().collect(Collectors.groupingBy(value -> value.getGroupId()));
            // 获取所有的分组ID
            Set<String> groupIdSet = byGourpMap.keySet();
            List<LocationVO> locationVOList = new ArrayList<>();
            for(String group:groupIdSet){
                // location /api01/ {
                //     rewrite ^/api01/(.*)$ /$1 break;
                // 	   proxy_pass http://api01;
                // }
                locationVOList.add(new LocationVO("/" + group + "/","http://" + group + ";"));
            }

            List<UpstreamVO> upstreamVOList = new ArrayList<>();
            for(String group:groupIdSet){
                // upstream api01 {
                //     least_conn;
                //     server 124.221.25.145:9001;
                //     #server 124.221.25.145:9002;
                // }
                List<String> serverAddress = byGourpMap.get(group).stream().map(detail -> detail.getGatewayAddress()).collect(Collectors.toList());
                upstreamVOList.add(new UpstreamVO(group,"least_conn;",serverAddress));
            }
            // 刷新nginx配置
            loadBalancingService.updateNginxConfig(new NginxConfig(upstreamVOList,locationVOList));
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), done);
        } catch (Exception e) {
            logger.error("注册网关服务节点异常", e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

    @PostMapping("distributeGateway")
    public void distributionGatewayServerNode(@RequestParam String groupId, @RequestParam String gatewayId){

    }

    @PostMapping("queryApplicationSystemRichInfo")
    public Result<ApplicationSystemRichInfo> queryApplicationSystemRichInfo(@RequestParam String gatewayId){
        try {
            logger.info("查询分配到网关下的待注册系统信息(系统、接口、方法) gatewayId：{}",gatewayId);
            ApplicationSystemRichInfo applicationSystemRichInfo = configManageService.queryApplicationSystemRichInfo(gatewayId);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), applicationSystemRichInfo);
        } catch (Exception e) {
            logger.error("获取分配到网关下的待注册系统信息异常", e);
            throw e;
        }

    }

    @PostMapping("queryRedisConfig")
    public Result<Map<String,String>> queryRedisConfig(){
        try {
            Map<String, String> redisConfig = messageService.queryRedisConfig();
            logger.info("查询redis配置信息:{}",redisConfig);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), redisConfig);
        } catch (Exception e) {
            logger.error("查询redis配置信息异常", e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }

    @GetMapping("quryGatewayServer")
    public Result<List<GatewayServerDataVO>> queryGatewayServer(@RequestParam GatewayServerDto gatewayServerDto){
        try {
            logger.info("查询网关服务配置项信息 groupId：{}",gatewayServerDto.getGroupId());
            List<GatewayServerDataVO> gatewayServerVOS = configManageService.queryGatewayServer(gatewayServerDto);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), gatewayServerVOS);
        } catch (Exception e) {
            logger.error("查询网关服务配置项信息异常", e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), null);
        }
    }

    @GetMapping("queryGatewayServerDetail")
    public Result<List<GatewayServerDetailDataVO>> queryGatewayServerDetailPage(GatewayServerDetailDto gatewayDetailRequest){
        try {
            logger.info("查询网关服务配置项详细信息 groupId：{}",gatewayDetailRequest.getGroupId());
            List<GatewayServerDetailDataVO> gatewayDetailVOS = configManageService.queryGatewayServerDetailPage(gatewayDetailRequest);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), gatewayDetailVOS);
        } catch (Exception e) {
            logger.error("查询网关服务配置项详细信息异常", e);
            throw e;
        }
    }

    @GetMapping("queryGatewayDistribution")
    public Result<List<GatewayDistributionDataVO>> queryGatewayDistribution(GatewayDistributionDto gatewayDistributionDto){
        logger.info("查询网关分配数据开始 groupId：{} gatewayId：{} page：{} limit：{}", gatewayDistributionDto.getGroupId(), gatewayDistributionDto.getGatewayId(), gatewayDistributionDto.getPageStart(), gatewayDistributionDto.getPageEnd());
        try {
            List<GatewayDistributionDataVO> gatewayDistributionVOList = configManageService.queryGatewayDistribution(gatewayDistributionDto);
            logger.info("查询网关分配数据完成 operationResult：{}", gatewayDistributionVOList);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), gatewayDistributionVOList);
        } catch (Exception e) {
            logger.error("查询网关分配数据异常 groupId：{}", gatewayDistributionDto.getGroupId(), e);
            throw e;
        }
    }

    @GetMapping("queryApplicationSystem")
    public Result<List<ApplicationSystemVO>> queryApplicationSystem(ApplicationSystemDto applicationSystemRequest){
        return invokePageQuery(applicationSystemRequest,configManageService::queryApplicationSystem,(request,logger)->logger.info("查询应用服务配置项信息,systemId:{},systemName:{}",request.getSystemId(),request.getSystemName()));
    }

    @GetMapping("queryApplicationInterface")
    public Result<List<ApplicationInterfaceDataVO>> queryApplicationInterface(ApplicationInterfaceDto interfaceRequest){
        return invokePageQuery(interfaceRequest,configManageService::queryApplicationInterfacePage,(request,logger)->logger.info("查询应用接口配置项信息,systemId:{},interfaceId:{}",request.getSystemId(),request.getInterfaceId()));
    }

    @GetMapping("queryApplicationInterfaceMethodList")
    public Result<List<ApplicationInterfaceMethodDataVO>> queryApplicationInterfaceMethodList(ApplicationInterfaceMethodDto methodRequest){
        return invokePageQuery(methodRequest,configManageService::queryApplicationInterfaceMethodPage,(request,logger)->logger.info("查询应用接口方法配置项信息,systemId:{},interfaceId:{},methodId:{}",request.getSystemId(),request.getInterfaceId()));
    }

    public<T,R> Result<R> invokePageQuery(T request, Function<T,R> queryFunction, BiConsumer<T,Logger> loggerFunction){
        loggerFunction.accept(request,logger);
        try {
            R resData = queryFunction.apply(request);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), resData);
        } catch (Exception e) {
            logger.error("查询应用服务配置项信息异常", e);
            throw e;
        }
    }


}
