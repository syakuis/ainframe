package org.ainframe.web.config.service;

import static org.junit.Assert.*;

import org.ainframe.context.ConfigContext;
import org.ainframe.context.ConfigContextService;
import org.ainframe.web.config.model.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 31.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ConfigContextServiceTest {
    @Autowired
    private ConfigService configService;
    @Autowired
    private ConfigContextService configContextService;

    @Autowired
    private ConfigContext configContext;

    @Test
    public void test() {
        Config configObject = configService.getConfig();
        Config configObject2 = configService.getConfig();
        assertEquals(configObject, configObject2);
        assertSame(configObject, configObject2);

        Config config = configContextService.getConfig();
        assertNotNull(config.getModuleIdx());
        assertEquals(config, configContext.getConfig());
        assertSame(config, configContext.getConfig());
    }
}
