package com.kevin.gateway.interfaces;

import com.kevin.gateway.application.IConfigManageService;
import com.kevin.gateway.application.IMessageService;
import com.kevin.gateway.application.IRegistryManageService;
import com.kevin.gateway.domain.registry.model.vo.ApplicationInterfaceMethodVO;
import com.kevin.gateway.domain.registry.model.vo.ApplicationInterfaceVO;
import com.kevin.gateway.domain.registry.model.vo.ApplicationSystemVO;
import com.kevin.gateway.domain.registry.repository.IRegistryManageRepository;
import com.kevin.gateway.infrustructs.common.ResponseCode;
import com.kevin.gateway.infrustructs.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wang
 * @create 2024-01-02-19:05
 */
@RestController
@RequestMapping("/wg/admin/register")
public class RpcRegisterManage {
    private static final Logger logger = LoggerFactory.getLogger(RpcRegisterManage.class);
    @Resource
    private IRegistryManageService registryManageService;

    @Resource
    private IConfigManageService configManageService;

    @Resource
    private IMessageService messageService;


    @PostMapping("registryApplication")
    public Result<Boolean> registryApplication(ApplicationSystemVO applicationSystemVO){
        try {
            logger.info("开始注册应用服务 systemId：{}", applicationSystemVO.getSystemId());
            registryManageService.registryApplication(applicationSystemVO);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        }catch (DuplicateKeyException e){
            logger.error("注册应用服务重复 systemId：{}", applicationSystemVO.getSystemId(), e);
            return new Result<>(ResponseCode.INDEX_DUP.getCode(), e.getMessage(), true);
        }
        catch (Exception e) {
            logger.error("注册应用服务失败 systemId：{}", applicationSystemVO.getSystemId(), e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

    @PostMapping("registryApplicationInterface")
    public Result<Boolean> registryApplicationInterface(ApplicationInterfaceVO applicationInterfaceVO){
        try {
            logger.info("开始注册应用服务接口 interfaceId：{}", applicationInterfaceVO.getInterfaceId());
            registryManageService.registryApplicationInterface(applicationInterfaceVO);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        }catch (DuplicateKeyException e){
            logger.error("注册应用服务重复 interfaceId：{}", applicationInterfaceVO.getInterfaceId(), e);
            return new Result<>(ResponseCode.INDEX_DUP.getCode(), e.getMessage(), true);
        }
        catch (Exception e) {
            logger.error("注册应用服务失败 interfaceId：{}", applicationInterfaceVO.getInterfaceId(), e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

    @PostMapping("registryApplicationInterfaceMethod")
    public Result<Boolean> registryApplicationInterfaceMethod(ApplicationInterfaceMethodVO applicationInterfaceMethodVO){
        try {
            logger.info("开始注册应用服务接口方法 methodId：{}", applicationInterfaceMethodVO.getMethodId());
            registryManageService.registryApplicationInterfaceMethod(applicationInterfaceMethodVO);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        }catch (DuplicateKeyException e){
            logger.error("注册应用服务重复 methodId：{}", applicationInterfaceMethodVO.getMethodId(), e);
            return new Result<>(ResponseCode.INDEX_DUP.getCode(), e.getMessage(), true);
        }
        catch (Exception e) {
            logger.error("注册应用服务失败 methodId：{}", applicationInterfaceMethodVO.getMethodId(), e);
            return new Result<>(ResponseCode.UN_ERROR.getCode(), e.getMessage(), false);
        }
    }

    @PostMapping("registryEvent")
    public Result<Boolean> registryEvent(@RequestParam String systemId){
        logger.info("服务又重新注册了新的系统网关：{}",systemId);
        try {
            String gatewayId = configManageService.queryGatewayDistributionGatewayIdBySystemId(systemId);
            messageService.pushMessage(gatewayId,systemId);
            return new Result<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getInfo(), true);
        } catch (Exception e) {
            logger.error("服务注册新的系统发送事件失败:{}",e.getMessage());
            throw e;
        }
    }
}
