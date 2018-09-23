package org.ainframe.web.module.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.ainframe.context.module.Module;
import org.ainframe.web.module.domain.ModuleEntity;
import org.ainframe.web.module.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 27.
 */
@Service
@Transactional
public class ModuleService {
    private ModuleRepository moduleRepository;

    @Autowired
    public void setModuleRepository(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    private List<Module> transform(List<ModuleEntity> moduleEntities) {
        return Lists.newArrayList(Lists.transform(moduleEntities, new Function<ModuleEntity, Module>() {
            @Override
            public Module apply(ModuleEntity entity) {
                return ModuleEntity.transform(entity);
            }
        }));
    }

    public List<Module> getModules() {
        return this.transform(this.moduleRepository.findAll());
    }

    public Module getModule(String moduleId) {
        return ModuleEntity.transform(this.moduleRepository.findOneByModuleId(moduleId));
    }

    public Module getModuleByModuleIdx(String moduleIdx) {
        return ModuleEntity.transform(this.moduleRepository.findOneByModuleIdx(moduleIdx));
    }
}
