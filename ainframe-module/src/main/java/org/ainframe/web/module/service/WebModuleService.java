package org.ainframe.web.module.service;

import org.ainframe.web.module.domain.ModuleEntity;
import org.ainframe.web.module.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 23.
 */
@Service
public class WebModuleService {
    private ModuleRepository moduleRepository;

    @Autowired
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
