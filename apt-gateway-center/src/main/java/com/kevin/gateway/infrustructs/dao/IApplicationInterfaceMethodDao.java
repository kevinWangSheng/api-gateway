package com.kevin.gateway.infrustructs.dao;

import com.kevin.gateway.domain.manage.model.dto.ApplicationInterfaceMethodDto;
import com.kevin.gateway.infrustructs.po.ApplicationInterfaceMethod;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-02-18:27
 */
public interface IApplicationInterfaceMethodDao {
    void insert(ApplicationInterfaceMethod applicationInterfaceMethod);

    List<ApplicationInterfaceMethod> queryMethodListBySystemIdsAndInterfaceIds(@Param("systemIds")List<String> systemIdList, @Param("interfaceIds")List<String> interfaceIds);

    List<ApplicationInterfaceMethod> queryPageBySystemIdAndInterfaceId(ApplicationInterfaceMethodDto applicationInterfaceMethodDto);
}
