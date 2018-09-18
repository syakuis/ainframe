package org.ainframe.web.menu.service;

import org.ainframe.context.MenuContextService;
import org.ainframe.context.model.MenuDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Cacheable(key = "'menuDetails_' + #menuIdx")
    @Override
    public MenuDetails getMenuDetails(String menuIdx) {
        return webMenuContextService.getMenuDetails(menuIdx);
    }

    @CacheEvict(key = "'menuDetails_' + #menuIdx")
    public void clear(String menuIdx) {
    }
}
