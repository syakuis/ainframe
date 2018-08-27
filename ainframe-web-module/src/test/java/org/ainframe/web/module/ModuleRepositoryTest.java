package org.ainframe.web.module;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.ainframe.web.module.domain.ModuleEntity;
import org.ainframe.web.module.domain.ModuleOptionEntity;
import org.ainframe.web.module.repository.ModuleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@Transactional
public class ModuleRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ModuleRepository moduleRepository;

    @Test
    public void 모듈() {
        ModuleEntity moduleEntity = new ModuleEntity("test", "test");
        moduleRepository.save(moduleEntity);
        assertNotNull(moduleEntity.getModuleIdx());
        ModuleEntity aModuleEntity = moduleRepository.findOne(moduleEntity.getModuleIdx());
        assertNotNull(aModuleEntity);
        assertEquals(moduleEntity, aModuleEntity);
        entityManager.flush();
        List<ModuleEntity> moduleEntities = moduleRepository.findAll();
        assertTrue(moduleEntities.size() == 1);
    }

    @Test
    public void 모듈과모듈옵션() {
        ModuleEntity moduleEntity = new ModuleEntity("test", "test");
        moduleEntity.setModuleOptionEntities(ModuleEntity.createModuleOptionEntities(
            new ModuleOptionEntity("op1", "op1"),
            new ModuleOptionEntity("op2", "op2")
        ));

        moduleRepository.save(moduleEntity);

        ModuleEntity aModuleEntity = moduleRepository.findOne(moduleEntity.getModuleIdx());

        assertTrue(aModuleEntity.getModuleOptionEntities().size() == 2);

        assertEquals(aModuleEntity.getModuleOptionEntities(), moduleEntity.getModuleOptionEntities());

        aModuleEntity.setModuleOptionEntities(Collections.<ModuleOptionEntity>emptyList());
        entityManager.flush();
        aModuleEntity = moduleRepository.findOne(aModuleEntity.getModuleIdx());

        assertTrue(aModuleEntity.getModuleOptionEntities().size() == 0);
    }
}
