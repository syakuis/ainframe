package org.ainframe.web.module.service;

import java.util.List;

import org.ainframe.context.ModuleContextService;
import org.ainframe.context.model.Module;
import org.ainframe.web.module.domain.ModuleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 29.
 */
@Service
@Transactional(readOnly = true)
public class WebModuleContextService implements ModuleContextService {
    private ModuleService moduleService;

    @Autowired
    public void setModuleService(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    private List<Module> transform(List<ModuleEntity> moduleEntities) {
        return Lists.transform(moduleEntities, new Function<ModuleEntity, Module>() {
            @Override
            public Module apply(ModuleEntity entity) {
                return ModuleEntity.transform(entity);
            }
        });
    }

    @Override
    public List<Module> getModules() {
        return this.transform(this.moduleService.getModules());
    }

    @Override
    public Module getModule(String moduleId) {
        return ModuleEntity.transform(this.moduleService.getModuleByModuleId(moduleId));
    }

    @Override
    public Module getModuleByModuleIdx(String moduleIdx) {
        return ModuleEntity.transform(this.moduleService.getModuleByModuleIdx(moduleIdx));
    }
}
