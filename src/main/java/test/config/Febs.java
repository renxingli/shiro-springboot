package test.config;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author A stubborn man
 * @create 2021/3/17 10:57
 * @Description
 */

@Data
@SpringBootConfiguration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = Febs.PROPERTIES_PREFIX)
public class Febs {
    public static final String PROPERTIES_PREFIX = "febs";
    public static final String ENABLE_REDIS_CACHE = "febs.enable-redis-cache";

    private ShiroProperties shiro = new ShiroProperties();
}
