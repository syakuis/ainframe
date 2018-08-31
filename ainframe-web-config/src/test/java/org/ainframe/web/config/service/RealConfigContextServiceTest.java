package org.ainframe.web.config.service;

import static org.junit.Assert.*;

import org.ainframe.web.config.context.ConfigContext;
import org.ainframe.web.config.domain.ConfigObject;
import org.ainframe.web.config.model.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
@EnableAutoConfiguration
@ActiveProfiles("real")
@Transactional(readOnly = true)
public class RealConfigContextServiceTest {
    @Autowired
    private ConfigService configService;
    @Autowired
    private ConfigContextService configContextService;

    @Autowired
    private ConfigContext configContext;

    @Test
    public void crud() {
        ConfigObject configObject = configService.getConfig();
        ConfigObject configObject2 = configService.getConfig();
        assertEquals(configObject, configObject2);
        assertTrue(configObject == configObject2);

        // ModelMapper 때문에 객체가 새로 만들어진다. 동등성은 맞아도 동일성은 맞지 않다.
        Config config = configContextService.getConfig();
        Config config2 = configContextService.getConfig();
        assertEquals(config, config2);
        assertTrue(config != config2);

        assertNotNull(config.getModuleIdx());
        assertEquals(config, configContext.getConfig());
        assertTrue(config != configContext.getConfig());
    }
}
