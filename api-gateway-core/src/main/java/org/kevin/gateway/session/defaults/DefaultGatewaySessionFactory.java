package org.kevin.gateway.session.defaults;

import org.kevin.gateway.session.Configuration;
import org.kevin.gateway.session.GatewaySession;
import org.kevin.gateway.session.GatewaySessionFactory;

/**
 * @author wang
 * @create 2023-12-29-19:12
 */
public class DefaultGatewaySessionFactory implements GatewaySessionFactory {
    private Configuration configuration;

    public DefaultGatewaySessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public GatewaySession openSession() {
        return new DefaultGatewaySession(configuration);
    }
}
