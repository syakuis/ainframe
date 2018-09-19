package org.ainframe.web.menu;

import static org.junit.Assert.assertEquals;

import org.ainframe.web.menu.domain.MenuEntity;
import org.ainframe.web.menu.service.CacheMenuServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("real")
@Transactional
public class MenuServiceTest {
    @Autowired
    private CacheMenuServiceTest menuService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void 서비스캐시테스트() {
        Cache cache = cacheManager.getCache(CacheMenuServiceTest.CACHE_NAME);
        MenuEntity menuEntity = menuService.getMenuWithMenuItem("MENU00000000000ADMIN");

        assertEquals(cache.get("MENU00000000000ADMIN", MenuEntity.class), menuEntity);
    }
}
