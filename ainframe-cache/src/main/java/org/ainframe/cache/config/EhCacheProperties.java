package org.ainframe.cache.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 10.
 */
@Configuration
@PropertySource("classpath:ainframe/cache.properties")
@ConfigurationProperties(prefix = "ainframe.cache.ehcache")
@Data
public class EhCacheProperties {
    private boolean enable;
    private String config;
    private String cacheConfig;
    private String charset;
}
