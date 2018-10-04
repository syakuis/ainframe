package org.ainframe.web.config.repository;

import org.ainframe.web.config.config.ConfigProperties;
import org.ainframe.web.config.domain.ConfigEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ConfigRepositoryTest {
    @Autowired
    private ConfigProperties configProperties;

    @Autowired
    private ConfigRepository configRepository;

    @Test
    public void test() {
        assertNotNull(configProperties.getModuleIdx());
        assertEquals(configProperties.getModuleIdx(),
            configRepository.findOneByModuleIdx(configProperties.getModuleIdx()).getModuleIdx());

        configRepository.deleteByModuleIdx(configProperties.getModuleIdx());

        ConfigEntity configEntity = ConfigEntity.builder()
                .moduleIdx(configProperties.getModuleIdx())
                .layoutIdx("LAOUT00000000DEFAULT")
                .basicSkin("cdc")
                .skin("gbdc")
                .indexUrl("/main")
                .build();
        configRepository.save(configEntity);

        assertEquals(configEntity, configRepository.findOneByModuleIdx(configProperties.getModuleIdx()));
        assertSame(configEntity, configRepository.findOneByModuleIdx(configProperties.getModuleIdx()));

    }
}
