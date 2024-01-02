package com.kevin.gateway.infrustructs.repository;

import com.kevin.gateway.domain.manage.model.vo.ApiDataVO;
import com.kevin.gateway.domain.manage.repository.IApiRepository;
import com.kevin.gateway.infrustructs.dao.HttpStatementDao;
import com.kevin.gateway.infrustructs.po.HttpStatement;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @create 2024-01-01-0:36
 */
@Component
public class ApiRepository implements IApiRepository {
    @Resource
    private HttpStatementDao httpStatementDao;
    @Override
    public List<ApiDataVO> getAllApiData() {
        List<HttpStatement> list = httpStatementDao.queryAllHttpStatement();
        if(null == list || list.size() == 0){
            return null;
        }
        List<ApiDataVO> apiDatas = new ArrayList<>();
        for(HttpStatement httpStatement : list){
            ApiDataVO apiData = new ApiDataVO();
            apiData.setMethodName(httpStatement.getMethodName());
            apiData.setParameterType(httpStatement.getParameterTypes());
            apiData.setUri(httpStatement.getUri());
            apiData.setHttpCommandType(httpStatement.getHttpCommandType());
            apiData.setAuth(httpStatement.getAuth());
            apiDatas.add(apiData);
        }
        return apiDatas;
    }
}
