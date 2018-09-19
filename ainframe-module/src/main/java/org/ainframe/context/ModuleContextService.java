package org.ainframe.context;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 11.
 */
public interface ModuleContextService {
    List<Module> getModules();

    Module getModule(String moduleId);

    Module getModuleByModuleIdx(String moduleIdx);
}
