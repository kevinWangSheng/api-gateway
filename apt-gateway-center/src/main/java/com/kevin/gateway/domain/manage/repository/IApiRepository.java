package com.kevin.gateway.domain.manage.repository;

import com.kevin.gateway.domain.manage.model.vo.ApiDataVO;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-01-0:35
 */
public interface IApiRepository {
    List<ApiDataVO> getAllApiData();
}
