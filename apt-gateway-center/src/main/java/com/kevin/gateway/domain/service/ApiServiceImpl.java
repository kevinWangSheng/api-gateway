package com.kevin.gateway.domain.service;

import com.kevin.gateway.application.IApiService;
import com.kevin.gateway.domain.model.ApiData;
import com.kevin.gateway.domain.repository.IApiRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wang
 * @create 2024-01-01-0:40
 */
@Service
public class ApiServiceImpl implements IApiService {
    @Resource
    private IApiRepository apiRepository;


    @Override
    public List<ApiData> queryAllApiData() {
        return apiRepository.getAllApiData();
    }
}
