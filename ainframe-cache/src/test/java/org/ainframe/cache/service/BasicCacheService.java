package org.ainframe.cache.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 7. 27.
 */
@Service
@CacheConfig(cacheNames = "test")
public class BasicCacheService {

    @Cacheable
    public long getDate() {
        return System.currentTimeMillis();
    }

    @CacheEvict
    public long evict() {
        return System.currentTimeMillis();
    }

    @CachePut
    public long put() {
        return System.currentTimeMillis();
    }

    @Cacheable(key = "#root.methodName")
    public long getDateKey() {
        return System.currentTimeMillis();
    }

    @Cacheable(key = "#root.methodName")
    public long getDateKey2() {
        return System.currentTimeMillis();
    }

    @CacheEvict(key = "#key")
    public long evictKey(String key) {
        return System.currentTimeMillis();
    }

    @CachePut(key = "#key")
    public long putKey(String key) {
        return System.currentTimeMillis();
    }
}