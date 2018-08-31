package org.ainframe.web.module.context;

import java.util.List;

import org.ainframe.web.module.model.Module;
import org.ainframe.web.module.service.ModuleContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 29.
 */
@Component
public final class ModuleContext {
    private ModuleContextService moduleContextService;

    @Autowired
    public void setModuleContextService(ModuleContextService moduleContextService) {
        this.moduleContextService = moduleContextService;
    }

    public List<Module> getModules() {
        return this.moduleContextService.getModules();
    }

    public Module getModule(String moduleId) {
        return this.moduleContextService.getModule(moduleId);
    }

    public Module getModuleByModuleIdx(String moduleIdx) {
        return this.moduleContextService.getModuleByModuleIdx(moduleIdx);
    }

    /**
     * moduleId 와 연관되는 moduleIdx 찾아 반환한다. 주의!!! moduleIdx 외에 여러 값을 참조할때는 getModule() 사용한다.
     * @param moduleId moduleId
     * @return String moduleIdx
     * @see ModuleContext#getModule(String)
     */
    public String getModuleIdxByModuleId(String moduleId) {
        return Optional.fromNullable(this.getModule(moduleId).getModuleIdx()).orNull();
    }

    /**
     * moduleIdx 와 연관되는 moduleId 찾아 반환한다. 주의!!! moduleId 외에 여러 값을 참조할때는 getModuleByModuleIdx() 사용한다.
     * @param moduleIdx moduleIdx
     * @return String moduleId
     * @see ModuleContext#getModuleByModuleIdx(String)
     */
    public String getModuleIdByModuleIdx(String moduleIdx) {
        return Optional.fromNullable(this.getModuleByModuleIdx(moduleIdx).getModuleIdx()).orNull();
    }

    /**
     * moduleId 와 연관되는 moduleName 찾아 반환한다. 주의!!! moduleName 외에 여러 값을 참조할때는 getModule() 사용한다.
     * @param moduleId moduleId
     * @return String moduleName
     * @see ModuleContext#getModule(String)
     */
    public String getModuleNameByModuleId(String moduleId) {
        return Optional.fromNullable(this.getModule(moduleId).getModuleName()).orNull();
    }

    /**
     * moduleIdx 와 연관되는 moduleName 찾아 반환한다. 주의!!! moduleName 외에 여러 값을 참조할때는 getModuleByModuleIdx() 사용한다.
     * @param moduleIdx moduleIdx
     * @return String moduleName
     * @see ModuleContext#getModuleByModuleIdx(String)
     */
    public String getModuleNameByModuleIdx(String moduleIdx) {
        return Optional.fromNullable(this.getModuleByModuleIdx(moduleIdx).getModuleName()).orNull();
    }
}
