package org.ainframe.cache.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 10.
 */
@ConfigurationProperties(prefix = "ainframe.cache.ehcache")
public class EhCacheProperties {
    @Getter @Setter
    private String config;
    @Getter @Setter
    private String cacheConfig;
    @Getter @Setter
    private String charset;
}
