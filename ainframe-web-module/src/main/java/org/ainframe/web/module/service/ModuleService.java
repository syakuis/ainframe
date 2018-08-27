package org.ainframe.web.module.service;

import java.util.List;

import javax.transaction.Transactional;

import org.ainframe.web.module.domain.ModuleEntity;
import org.ainframe.web.module.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 27.
 */
@Service
@Transactional
public class ModuleService {
    private final ModuleRepository moduleRepository;

    @Autowired
    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public List<ModuleEntity> getModules() {
        return this.moduleRepository.findAll();
    }

    public long getModuleTotalCount() {
        return this.moduleRepository.count();
    }
}
