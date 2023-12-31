package org.kevin.gateway.bind;

import org.kevin.gateway.executor.result.SessionResult;

import java.util.Map;

/**
 * @author wang
 * @create 2023-12-28-21:46
 */
public interface IGenericReference {

    SessionResult $invoke(Map<String,Object> args);
}
