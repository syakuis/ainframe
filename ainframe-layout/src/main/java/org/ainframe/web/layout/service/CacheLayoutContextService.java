package org.ainframe.web.layout.service;

import org.ainframe.context.Layout;
import org.ainframe.context.LayoutContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 18.
 */
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "layoutContext")
public class CacheLayoutContextService implements LayoutContextService {
    private LayoutContextService layoutContextService;

    @Autowired
    @Qualifier("webLayoutContextService")
    public void setLayoutContextService(LayoutContextService layoutContextService) {
        this.layoutContextService = layoutContextService;
    }

    @Cacheable(key = "#layoutIdx")
    @Override
    public Layout getLayout(String layoutIdx) {
        return this.layoutContextService.getLayout(layoutIdx);
    }

    @Override
    public Map<String, String> getAllLayoutName() {
        return this.layoutContextService.getAllLayoutName();
    }

    @CacheEvict("'getAllLayoutName'")
    public void clear() {
    }

    @Caching(evict = {@CacheEvict("'getAllLayoutName'"), @CacheEvict("#layoutIdx")})
    public void clear(String layoutIdx) {

    }
}
