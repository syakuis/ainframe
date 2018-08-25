package org.ainframe.web.module;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.ainframe.web.module.repository.ModuleRepository;
import org.ainframe.web.module.domain.ModuleEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 24.
 */
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest
@DataJpaTest
@Transactional
public class ModuleServiceTest {
    @Configuration
    static class Config {

    }

    @Autowired
    private ModuleRepository moduleRepository;

    @Test
    @Commit
    public void 모듈정보모두가져오기() {
        List<ModuleEntity> moduleEntities = moduleRepository.findAll();
        assertTrue(moduleEntities.isEmpty());

        ModuleEntity moduleEntity = new ModuleEntity("test", "test");
        moduleRepository.save(moduleEntity);

        assertNotNull(moduleEntity.getModuleIdx());

        assertNotNull(moduleRepository.findOne(moduleEntity.getModuleIdx()));


    }
}
