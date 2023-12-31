package org.kevin.gateway.authorization;

/**
 * @author wang
 * @create 2023-12-31-0:20
 */
public interface IAuth {
    boolean validate(String id, String token);
}
