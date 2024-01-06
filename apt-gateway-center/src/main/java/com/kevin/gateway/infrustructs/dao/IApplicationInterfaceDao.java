package com.kevin.gateway.infrustructs.dao;

import com.kevin.gateway.domain.manage.model.dto.ApplicationInterfaceDto;
import com.kevin.gateway.infrustructs.po.ApplicationInterface;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-02-18:34
 */
public interface IApplicationInterfaceDao {
    void insert(ApplicationInterface applicationInterface);

    List<ApplicationInterface> queryInterfaceListBySystemIds(@Param("systemIds") List<String> systemIdList);

    List<ApplicationInterface> queryPageBySystemIdAndInterfaceId(ApplicationInterfaceDto applicationInterfaceDto);
}
