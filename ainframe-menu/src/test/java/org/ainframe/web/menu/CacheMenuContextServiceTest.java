package org.ainframe.web.menu;

import org.ainframe.web.menu.model.Menu;
import org.ainframe.web.menu.service.CacheMenuContextService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CacheMenuContextServiceTest {
    @Value("${ainframe.web.menu.contextCacheName}")
    private String contextCacheName;

    @Autowired
    private CacheMenuContextService cacheMenuContextService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void 서비스캐시테스트() {
        String menuIdx = "MENU00000000000ADMIN";
        Menu menu = cacheMenuContextService.getMenu(menuIdx);

        Cache cache = cacheManager.getCache(contextCacheName);
        assertEquals(cache.get(menuIdx, Menu.class), menu);
    }
}
