package org.ainframe.web.module.service;

import java.util.List;

import org.ainframe.web.module.domain.ModuleEntity;
import org.ainframe.web.module.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 27.
 */
@Service
@Transactional
public class ModuleService {
    private ModuleRepository moduleRepository;

    @Autowired
    @Qualifier("moduleRepository")
    public void setModuleRepository(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public List<ModuleEntity> getModules() {
        return this.moduleRepository.findAll();
    }

    public ModuleEntity getModuleByModuleId(String moduleId) {
        return this.moduleRepository.findOneByModuleId(moduleId);
    }

    public ModuleEntity getModuleByModuleIdx(String moduleIdx) {
        return this.moduleRepository.findOneByModuleId(moduleIdx);
    }

    public long getModuleTotalCount() {
        return this.moduleRepository.count();
    }
}
