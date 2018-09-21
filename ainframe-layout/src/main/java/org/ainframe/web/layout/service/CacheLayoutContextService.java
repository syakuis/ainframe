package org.ainframe.web.layout.service;

import org.ainframe.context.Layout;
import org.ainframe.context.LayoutContextService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private WebLayoutContextService webLayoutContextService;

    @Autowired
    public void setWebLayoutContextService(WebLayoutContextService webLayoutContextService) {
        this.webLayoutContextService = webLayoutContextService;
    }

    @Cacheable(key = "#layoutIdx")
    @Override
    public Layout getLayout(String layoutIdx) {
        return this.webLayoutContextService.getLayout(layoutIdx);
    }

    @Override
    public Map<String, String> getAllLayoutName() {
        return this.webLayoutContextService.getAllLayoutName();
    }

    @CacheEvict("'getAllLayoutName'")
    public void clear() {
    }

    @Caching(evict = {@CacheEvict("'getAllLayoutName'"), @CacheEvict("#layoutIdx")})
    public void clear(String layoutIdx) {

    }
}
