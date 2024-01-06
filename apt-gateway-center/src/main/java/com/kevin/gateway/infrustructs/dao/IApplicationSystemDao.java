package com.kevin.gateway.infrustructs.dao;

import com.kevin.gateway.infrustructs.po.ApplicationSystem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-02-18:47
 */
public interface IApplicationSystemDao {
    void insert(ApplicationSystem applicationSystem);


    List<ApplicationSystem> querySystemListBySystemIds(@Param("systemIds") List<String> systemIdList);
}
