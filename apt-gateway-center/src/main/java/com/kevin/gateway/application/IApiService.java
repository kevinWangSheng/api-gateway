package com.kevin.gateway.application;

import com.kevin.gateway.domain.model.ApiData;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-01-0:39
 */
public interface IApiService {
    List<ApiData> queryAllApiData();
}
