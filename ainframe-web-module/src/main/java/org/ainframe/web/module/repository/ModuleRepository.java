package org.ainframe.web.module.repository;

import org.ainframe.web.module.domain.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 24.
 */
public interface ModuleRepository extends JpaRepository<ModuleEntity, String> {
}
