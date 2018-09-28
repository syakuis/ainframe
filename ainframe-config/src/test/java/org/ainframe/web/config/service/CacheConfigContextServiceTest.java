package org.ainframe.web.config.service;

import org.ainframe.context.ConfigContext;
import org.ainframe.context.ConfigContextService;
import org.ainframe.web.config.config.ConfigProperties;
import org.ainframe.web.config.model.Config;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class CacheConfigContextServiceTest {
    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private ConfigContext configContext;

    @Autowired
    @Qualifier("cacheConfigContextService")
    private ConfigContextService configContextService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void test() {
        Config config = configContextService.getConfig();
        assertEquals(config,
            cacheManager.getCache(configProperties.getContextCacheName()).get("config", Config.class));
        assertSame(config,
            cacheManager.getCache(configProperties.getContextCacheName()).get("config", Config.class));
    }


    @Test
    public void contextTest() {
        Config config = configContextService.getConfig();
        assertEquals(config, configContext.getConfig());
        assertSame(config, configContext.getConfig());
    }
}
