package org.ainframe.context.module;

import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 29.
 */
@Component
public final class ModuleContext {
    private ModuleContextService moduleContextService;

    @Autowired
    @Qualifier("cacheModuleContextService")
    public void setModuleContextService(ModuleContextService moduleContextService) {
        this.moduleContextService = moduleContextService;
    }

    private ModuleStore store() {
        return this.moduleContextService.getModuleStore();
    }

    public List<Module> getModules() {
        return this.store().values();
    }

    public Module getModule(String moduleId) {
        return this.store().getModule(moduleId);
    }

    public Module getModuleByModuleIdx(String moduleIdx) {
        return this.store().getModuleByModuleIdx(moduleIdx);
    }

    /**
     * moduleId 와 연관되는 moduleIdx 찾아 반환한다. 주의!!! moduleIdx 외에 여러 값을 참조할때는 getModule() 사용한다.
     * @param moduleId moduleId
     * @return String moduleIdx
     * @see ModuleContext#getModule(String)
     */
    public String getModuleIdxByModuleId(String moduleId) {
        return Optional.fromNullable(this.store().getModuleIdx(moduleId)).orNull();
    }

    /**
     * moduleIdx 와 연관되는 moduleId 찾아 반환한다. 주의!!! moduleId 외에 여러 값을 참조할때는 getModuleByModuleIdx() 사용한다.
     * @param moduleIdx moduleIdx
     * @return String moduleId
     * @see ModuleContext#getModuleByModuleIdx(String)
     */
    public String getModuleIdByModuleIdx(String moduleIdx) {
        return Optional.fromNullable(this.store().getModuleId(moduleIdx)).orNull();
    }

    /**
     * moduleId 와 연관되는 moduleName 찾아 반환한다. 주의!!! moduleName 외에 여러 값을 참조할때는 getModule() 사용한다.
     * @param moduleId moduleId
     * @return String moduleName
     * @see ModuleContext#getModule(String)
     */
    public String getModuleNameByModuleId(String moduleId) {
        return Optional.fromNullable(this.store().getModuleNameByModuleId(moduleId)).orNull();
    }

    /**
     * moduleIdx 와 연관되는 moduleName 찾아 반환한다. 주의!!! moduleName 외에 여러 값을 참조할때는 getModuleByModuleIdx() 사용한다.
     * @param moduleIdx moduleIdx
     * @return String moduleName
     * @see ModuleContext#getModuleByModuleIdx(String)
     */
    public String getModuleNameByModuleIdx(String moduleIdx) {
        return Optional.fromNullable(this.store().getModuleNameByModuleIdx(moduleIdx)).orNull();
    }
}
