package org.ainframe.web.menu.service;

import org.ainframe.web.menu.domain.MenuDetailsEntity;
import org.ainframe.web.menu.repository.MenuRepository;
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
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "menuService")
public class CacheMenuServiceTest {
    public static final String CACHE_NAME = "menuService";
    private MenuRepository menuRepository;

    @Autowired
    public void setMenuRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Cacheable(key = "#menuIdx")
    public MenuDetailsEntity getMenuWithMenuItem(String menuIdx) {
        return menuRepository.findMenuEntitiesByMenuIdx(menuIdx);
    }

    @CacheEvict(key = "#menuIdx")
    public void clear(String menuIdx) {
    }
}