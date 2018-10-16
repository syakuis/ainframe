package org.ainframe.web.module.repository;

import lombok.extern.slf4j.Slf4j;
import org.ainframe.web.module.domain.ModuleEntity;
import org.ainframe.web.module.domain.ModuleOptionEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 24.
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ModuleRepositoryTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ModuleRepository moduleRepository;

    @Test
    public void 모듈() {
        ModuleEntity moduleEntity = ModuleEntity.builder().moduleId("test").moduleName("test").build();
        moduleRepository.save(moduleEntity);
        assertNotNull(moduleEntity.getModuleIdx());
        ModuleEntity aModuleEntity = moduleRepository.findOne(moduleEntity.getModuleIdx());
        assertNotNull(aModuleEntity);
        assertEquals(moduleEntity, aModuleEntity);
        entityManager.flush();
        List<ModuleEntity> moduleEntities = moduleRepository.findAll();
        assertTrue(moduleEntities.size()>= 1);
    }

    @Test
    public void 모듈과모듈옵션() {
        ModuleEntity moduleEntity = ModuleEntity.builder()
            .moduleId("test")
            .moduleName("test")
            .moduleOptionEntities(ModuleEntity.createModuleOptionEntities(
                ModuleOptionEntity.builder().name("op1").value("op1").build(),
                ModuleOptionEntity.builder().name("op2").value("op2").build()
            )).build();
        moduleRepository.save(moduleEntity);

        ModuleEntity aModuleEntity = moduleRepository.findOne(moduleEntity.getModuleIdx());

        assertTrue(aModuleEntity.getModuleOptionEntities().size() == 2);

        assertEquals(aModuleEntity.getModuleOptionEntities(), moduleEntity.getModuleOptionEntities());

        aModuleEntity.setModuleOptionEntities(Collections.<ModuleOptionEntity>emptyList());
        entityManager.flush();
        aModuleEntity = moduleRepository.findOne(aModuleEntity.getModuleIdx());

        assertTrue(aModuleEntity.getModuleOptionEntities().size() == 0);
    }

    @Test
    public void 불변테스트() {
      ModuleEntity moduleEntity = ModuleEntity.builder()
          .moduleId("test")
          .moduleName("test")
          .moduleOptionEntities(ModuleEntity.createModuleOptionEntities(
              ModuleOptionEntity.builder().name("op1").value("op1").build(),
              ModuleOptionEntity.builder().name("op2").value("op2").build()
          )).build();
       moduleRepository.save(moduleEntity);

       assertEquals(moduleRepository.findOne(moduleEntity.getModuleIdx()), moduleEntity);

       moduleEntity.setBrowserTitle("good!");

       assertEquals(moduleRepository.findOne(moduleEntity.getModuleIdx()).getBrowserTitle(), "good!");

    }
}
