package org.ainframe.web.module.repository;

import org.ainframe.web.module.domain.ModuleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 16.
 */
public interface ModuleEntityRepository extends JpaRepository<ModuleEntity, String> {
  ModuleEntity getOneByModuleId(String moduleId);
  Page<ModuleEntity> findByModuleId(String moduleId, Pageable pageable);
  Page<ModuleEntity> findByModuleName(String moduleName, Pageable pageable);
  Page<ModuleEntity> findByBrowserTitleStartingWith(String browserTitle, Pageable pageable);
}
