package com.kevin.gateway.interfaces;

import com.kevin.gateway.application.IApiService;
import com.kevin.gateway.application.IConfigManageService;
import com.kevin.gateway.domain.manage.model.aggregates.ApplicationSystemRichInfo;
import com.kevin.gateway.domain.manage.model.vo.ApiDataVO;
import com.kevin.gateway.domain.manage.model.vo.GatewayServerVO;
import com.kevin.gateway.infrustructs.common.Constance;
import com.kevin.gateway.infrustructs.common.ResponseCode;
import com.kevin.gateway.infrustructs.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wang
 * @create 2024-01-01-0:41
 */
@RestController
@RequestMapping("/wg/admin/config")
public class GatewayConfigManageController {
    private static final Logger logger = LoggerFactory.getLogger(GatewayConfigManageController.class);
    @Resource
    private IApiService apiService;

    @Resource
    private IConfigManageService configManageService;

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



}
