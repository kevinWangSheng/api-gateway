package com.kevin.gateway.domain.repository;

import com.kevin.gateway.domain.model.ApiData;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-01-0:35
 */
public interface IApiRepository {
    List<ApiData> getAllApiData();
}
