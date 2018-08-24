package org.ainframe.cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 16. 7. 27.
 */
@Service
public class BasicCacheServiceTest {

    @Cacheable("test")
    public long getDate() {
        return System.currentTimeMillis();
    }

    @CacheEvict("test")
    public long evict() {
        return System.currentTimeMillis();
    }

    @CachePut("test")
    public long put() {
        return System.currentTimeMillis();
    }

    @Cacheable(cacheNames = "test", key = "#root.methodName")
    public long getDateKey() {
        return System.currentTimeMillis();
    }

    @Cacheable(cacheNames = "test", key = "#root.methodName")
    public long getDateKey2() {
        return System.currentTimeMillis();
    }

    @CacheEvict(value = "test", key = "#key")
    public long evictKey(String key) {
        return System.currentTimeMillis();
    }

    @CachePut(value = "test", key = "#key")
    public long putKey(String key) {
        return System.currentTimeMillis();
    }
}