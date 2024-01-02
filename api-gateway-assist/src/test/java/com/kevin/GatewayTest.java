package com.kevin;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.kevin.gateway.common.Result;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang
 * @create 2024-01-02-21:46
 */
public class GatewayTest {
    @Test
    public void test(){
        Map<String,Object> paramMap = new HashMap<>();

        paramMap.put("groupId", "10001");
        paramMap.put("gatewayId", "api-gateway-g4");
        paramMap.put("gatewayName", "电商配送网关");
        paramMap.put("gatewayAddress", "127.0.0.1");

        String resultStr = HttpUtil.post("http://localhost:8090/wg/admin/config/registryGateway", paramMap, 350);
        System.out.println(resultStr);
        Result result = JSON.parseObject(resultStr, Result.class);
        System.out.println(result.getCode());
    }
}
