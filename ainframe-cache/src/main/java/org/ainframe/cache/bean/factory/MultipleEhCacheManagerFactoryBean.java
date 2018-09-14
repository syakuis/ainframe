package org.ainframe.cache.bean.factory;

import org.ainframe.cache.bean.factory.support.EhCacheConfigurationLoader;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheException;

/**
 * ehcache xml 설정 파일을 하나 이상인 경우 ehcache manager 생성하기 위함.
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 7. 28.
 * @see org.springframework.cache.ehcache.EhCacheManagerFactoryBean
 */
@Slf4j
public class MultipleEhCacheManagerFactoryBean extends EhCacheManagerFactoryBean {
    private final String ehcacheLocation;
    private final String[] cacheLocations;
    private String charset = "UTF-8";

    /**
     * xml 언어셋
     * @param charset 언어셋
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    public MultipleEhCacheManagerFactoryBean(String ehcacheLocation, String cacheLocation) {
        this(ehcacheLocation, StringUtils.tokenizeToStringArray(cacheLocation, ","));
    }

    public MultipleEhCacheManagerFactoryBean(String ehcacheLocation, String[] cacheLocations) {
        this.ehcacheLocation = ehcacheLocation;
        this.cacheLocations = cacheLocations;
    }

    @Override
    public void afterPropertiesSet() throws CacheException {
        EhCacheConfigurationLoader loader = new EhCacheConfigurationLoader(ehcacheLocation, cacheLocations);
        loader.setCharset(charset);
        Resource configuration = loader.getResource();
        Assert.notNull(configuration, "The class must not be null");
        super.setConfigLocation(configuration);
        super.afterPropertiesSet();
    }
}