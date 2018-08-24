package org.ainframe.cache.bean.factory.support;

import net.sf.ehcache.CacheException;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 13.
 */
public class EhCacheConfigurationException extends CacheException {
    public EhCacheConfigurationException() {
        super();
    }

    public EhCacheConfigurationException(String message) {
        super(message);
    }

    public EhCacheConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public EhCacheConfigurationException(Throwable cause) {
        super(cause);
    }
}
