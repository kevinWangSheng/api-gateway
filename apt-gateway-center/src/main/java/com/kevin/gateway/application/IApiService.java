package com.kevin.gateway.application;

import com.kevin.gateway.domain.manage.model.vo.ApiDataVO;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-01-0:39
 */
public interface IApiService {
    List<ApiDataVO> queryAllApiData();


}
