package org.ainframe.web.config.service;

import org.ainframe.context.ConfigContextService;
import org.ainframe.web.config.domain.ConfigEntity;
import org.ainframe.web.config.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 31.
 * @see ConfigService
 * @see ConfigEntity
 */
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "configContext")
public class CacheConfigContextService implements ConfigContextService {
    private ConfigContextService configContextService;

    @Autowired
    @Qualifier("webConfigContextService")
    public void setConfigContextService(ConfigContextService configContextService) {
        this.configContextService = configContextService;
    }

    @Cacheable(key = "'config'")
    @Override
    public Config getConfig() {
        return this.configContextService.getConfig();
    }

    @CacheEvict(allEntries = true)
    public void allClear() {

    }
}
