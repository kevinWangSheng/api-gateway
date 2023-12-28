package com.kevin.gateway.sdk.annotation;

import java.lang.annotation.*;

/**
 * @author wang
 * @create 2023-12-29-1:19
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiProducerClazz {
    /** 接口名称 */
    String interfaceName() default "";

    /** 接口版本 */
    String interfaceVersion() default "";
}
