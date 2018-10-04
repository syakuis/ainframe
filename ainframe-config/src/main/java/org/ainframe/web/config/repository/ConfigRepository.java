package org.ainframe.web.config.repository;

import org.ainframe.web.config.domain.ConfigEntity;
import org.springframework.data.repository.Repository;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 30.
 */
public interface ConfigRepository extends Repository<ConfigEntity, String> {
    ConfigEntity save(ConfigEntity configEntity);
    ConfigEntity findOneByModuleIdx(String moduleIdx);
    String deleteByModuleIdx(String moduleIdx);
}
