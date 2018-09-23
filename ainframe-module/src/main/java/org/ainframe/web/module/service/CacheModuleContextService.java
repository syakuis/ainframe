package org.ainframe.web.module.service;

import org.ainframe.context.module.ModuleContextService;
import org.ainframe.context.module.ModuleStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 29.
 */
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "moduleContext")
public class CacheModuleContextService implements ModuleContextService {
    private ModuleService moduleService;

    @Autowired
    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    /**
     * 모듈 전용 스토어를 반환한다.
     * @return ModuleStore
     */
    @Override
    @Cacheable
    public ModuleStore getModuleStore() {
        return new ModuleStore(this.moduleService.getModules());
    }

    @CacheEvict(allEntries = true)
    public void allClear() {

    }
}
