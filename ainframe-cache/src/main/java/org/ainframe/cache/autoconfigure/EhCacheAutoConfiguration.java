package org.ainframe.cache.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.ainframe.cache.bean.factory.MultipleEhCacheManagerFactoryBean;
import org.ainframe.core.util.Label;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 16. 7. 22.
 */
@Slf4j
@Configuration
@ConditionalOnClass(name = "net.sf.ehcache.CacheManager")
@ConditionalOnProperty(name="ainframe.cache.ehcache.enable", havingValue="true")
@EnableConfigurationProperties(EhCacheProperties.class)
@EnableCaching
public class EhCacheAutoConfiguration implements CachingConfigurer {

    @Autowired
    private EhCacheProperties ehCacheProperties;

    @Bean(destroyMethod="shutdown")
    @ConditionalOnMissingBean(net.sf.ehcache.CacheManager.class)
    public net.sf.ehcache.CacheManager ehCacheManager() {
        MultipleEhCacheManagerFactoryBean factoryBean =
            new MultipleEhCacheManagerFactoryBean(
                ehCacheProperties.getConfig(), ehCacheProperties.getCacheConfig());
        factoryBean.setCharset(ehCacheProperties.getCharset());
        factoryBean.afterPropertiesSet();
        net.sf.ehcache.CacheManager cacheManager = factoryBean.getObject();

        new Label().title("EHCACHE STARTER")
            .first("Created Caches: ").add(StringUtils.join(cacheManager.getCacheNames(), ", "))
            .debug();
        return cacheManager;
    }

    @Bean
    @ConditionalOnMissingBean(CacheManager.class)
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return null;
    }
}
