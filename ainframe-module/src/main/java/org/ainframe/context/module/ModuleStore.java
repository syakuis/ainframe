package org.ainframe.context.module;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 23.
 */
public final class ModuleStore implements Serializable {
    private final Map<String, Module> store;
    private final List<Module> modules;
    private final Map<String, String> mapper;

    public ModuleStore(List<Module> modules) {
        this.modules = Lists.newArrayList(modules);
        this.store = new HashMap<>();
        this.mapper = new HashMap<>();

        for (Module module : this.modules) {
            this.store.put(module.getModuleId(), module);
            this.mapper.put(module.getModuleIdx(), module.getModuleId());
        }
    }

    private String getMapper(String moduleIdx) {
        if (!this.mapper.containsKey(moduleIdx)) {
            throw new IllegalArgumentException("moduleIdx not found in mapper");
        }

        return this.mapper.get(moduleIdx);
    }

    public List<Module> values() {
        return modules;
    }

    public Module getModule(String moduleId) {
        if (!this.store.containsKey(moduleId)) {
            throw new IllegalArgumentException("module not found");
        }
        return this.store.get(moduleId);
    }

    public Module getModuleByModuleIdx(String moduleIdx) {
        return this.getModule(this.getModuleId(moduleIdx));
    }

    public String getModuleId(String moduleIdx) {
        return this.getMapper(moduleIdx);
    }

    public String getModuleNameByModuleIdx(String moduleIdx) {
        return this.getModuleByModuleIdx(moduleIdx).getModuleName();
    }

    public String getModuleNameByModuleId(String moduleId) {
        return this.getModule(moduleId).getModuleName();
    }

    public String getModuleIdx(String moduleId) {
        return this.getModule(moduleId).getModuleIdx();
    }
}
