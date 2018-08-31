package org.ainframe.web.config.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.ainframe.web.config.config.ConfigProperties;
import org.ainframe.web.config.domain.ConfigEntity;
import org.ainframe.web.config.repository.ConfigRepository;
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
 * @since 2018. 8. 30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@ActiveProfiles("test")
@Transactional
public class ConfigRepositoryTest {
    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private ConfigRepository configRepository;

    @Test
    public void crud() {
        assertNotNull(configProperties.getModuleIdx());
        ConfigEntity configEntity = ConfigEntity.builder()
                .moduleIdx(configProperties.getModuleIdx())
                .basicSkin("cdc")
                .skin("gbdc")
                .indexPage("/main")
                .build();
        configRepository.save(configEntity);

        assertEquals(configEntity, configRepository.findOneByModuleIdx(configProperties.getModuleIdx()));
        assertTrue(configEntity == configRepository.findOneByModuleIdx(configProperties.getModuleIdx()));

    }
}
