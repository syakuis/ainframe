package org.ainframe.cache.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 10.
 */
@ConfigurationProperties(prefix = "ainframe.cache.ehcache")
@Getter @Setter
public class EhCacheProperties {
    private String config;
    private String cacheConfig;
    private String charset;
}
