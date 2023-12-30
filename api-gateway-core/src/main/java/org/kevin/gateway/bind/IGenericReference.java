package org.kevin.gateway.bind;

import java.util.Map;

/**
 * @author wang
 * @create 2023-12-28-21:46
 */
public interface IGenericReference {

    Object $invoke(Map<String,Object> args);
}
