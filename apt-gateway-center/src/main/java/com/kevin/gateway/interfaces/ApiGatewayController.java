package com.kevin.gateway.interfaces;

import com.kevin.gateway.application.IApiService;
import com.kevin.gateway.domain.model.ApiData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wang
 * @create 2024-01-01-0:41
 */
@RestController
@RequestMapping("/api")
public class ApiGatewayController {
    @Resource
    private IApiService apiService;

    @GetMapping("/list")
    public List<ApiData> list(){
        return apiService.queryAllApiData();
    }
}
