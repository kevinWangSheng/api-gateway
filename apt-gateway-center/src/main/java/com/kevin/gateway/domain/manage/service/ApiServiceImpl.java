package com.kevin.gateway.domain.manage.service;

import com.kevin.gateway.application.IApiService;
import com.kevin.gateway.domain.manage.model.vo.ApiDataVO;
import com.kevin.gateway.domain.manage.repository.IApiRepository;
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
    public List<ApiDataVO> queryAllApiData() {
        return apiRepository.getAllApiData();
    }
}
