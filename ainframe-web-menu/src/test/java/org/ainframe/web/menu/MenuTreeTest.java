package org.ainframe.web.menu;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.ainframe.web.menu.domain.MenuEntity;
import org.ainframe.web.menu.domain.MenuItemEntity;
import org.ainframe.web.menu.model.MenuTree;
import org.ainframe.web.menu.util.MenuTreeUtils;
import org.ainframe.web.menu.service.CacheMenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 12.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("real")
@Transactional
public class MenuTreeTest {
    @Autowired
    private CacheMenuService menuService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void 서비스캐시테스트() {
        Cache cache = cacheManager.getCache(CacheMenuService.CACHE_NAME);
        MenuEntity menuEntity = menuService.getMenu("MENU00000000000ADMIN");

        assertEquals(cache.get("MENU00000000000ADMIN", MenuEntity.class), menuEntity);
    }

    @Test
    public void 사이트이동경로테스트() {

    }

    @Test
    public void 메뉴를트리형태로만들기() {
        MenuEntity menuEntity = menuService.getMenu("MENU0000000000000003");
        List<MenuItemEntity> menuItemEntities = menuEntity.getMenuItemEntities();
        Collections.sort(menuItemEntities);
        for (MenuItemEntity menuItemEntity : menuItemEntities) {
            log.debug("{} = {} : {}", menuItemEntity.getTreeOrder(), menuItemEntity.getTreeName(), menuItemEntity.getParentId());
        }

        MenuTreeUtils menuTreeUtils = new MenuTreeUtils(menuItemEntities, "menu");
        menuTreeUtils.displayMapping();
        menuTreeUtils.displayMenuTree();
    }
}
