package com.kevin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wang
 * @create 2024-01-01-0:48
 */
@SpringBootApplication
@Configurable
@MapperScan(basePackages = "com.kevin.gateway.infrustructs.dao")
public class GatewayCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayCenterApplication.class,args);
    }
}
