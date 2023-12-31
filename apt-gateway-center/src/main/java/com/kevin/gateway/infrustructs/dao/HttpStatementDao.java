package com.kevin.gateway.infrustructs.dao;

import com.kevin.gateway.infrustructs.po.HttpStatement;

import java.util.List;

/**
 * @author wang
 * @create 2024-01-01-0:30
 */

public interface HttpStatementDao {
    List<HttpStatement> queryAllHttpStatement();
}
