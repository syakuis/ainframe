package org.ainframe.web.config.config;

import org.ainframe.core.module.ModuleProperties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 31.
 */
public interface ConfigConfig extends ModuleProperties {
    String getModuleName();

    String getModuleIdx();

    boolean isSingle();
}
