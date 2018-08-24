package org.ainframe.cache.autoconfigure;

import org.ainframe.cache.bean.factory.MultipleEhCacheManagerFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
@Configuration
@ConditionalOnClass(net.sf.ehcache.CacheManager.class)
@ConditionalOnMissingBean(type = "org.springframework.cache.CacheManager")
@EnableConfigurationProperties(EhCacheProperties.class)
@EnableCaching
public class EhCacheAutoConfiguration implements CachingConfigurer {

    @Autowired
    private EhCacheProperties ehCacheProperties;

    @Bean(destroyMethod="shutdown")
    @ConditionalOnMissingBean
    public net.sf.ehcache.CacheManager ehCacheManager() {
        MultipleEhCacheManagerFactoryBean factoryBean =
            new MultipleEhCacheManagerFactoryBean(
                ehCacheProperties.getConfig(), ehCacheProperties.getCacheConfig());
        factoryBean.setCharset(ehCacheProperties.getCharset());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    @ConditionalOnMissingBean
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
