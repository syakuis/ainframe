package org.ainframe.web.menu.service;

import org.ainframe.context.MenuContextService;
import org.ainframe.web.menu.model.Menu;
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
 * @since 2018. 9. 13.
 */
@Service
@Transactional
@CacheConfig(cacheNames = "menuContext")
public class CacheMenuContextService implements MenuContextService {
    private WebMenuContextService webMenuContextService;

    @Autowired
    public void setWebMenuContextService(WebMenuContextService webMenuContextService) {
        this.webMenuContextService = webMenuContextService;
    }

    @Cacheable(key = "#menuIdx")
    @Override
    public Menu getMenu(String menuIdx) {
        return webMenuContextService.getMenu(menuIdx);
    }

    @Override
    @Cacheable
    public Map<String, String> getAllMenuName() {
        return webMenuContextService.getAllMenuName();
    }

    @Caching(
        evict={ @CacheEvict(key = "#menuIdx"), @CacheEvict(key = "'getAllMenuName'")}
    )
    public void clear(String menuIdx) {
    }
}
