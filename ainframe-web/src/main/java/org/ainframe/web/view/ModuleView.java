package org.ainframe.web.view;

import org.ainframe.context.module.Module;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 28.
 */
public interface ModuleView {
    Module getModule();
    /**
     * @return moduleName
     */
    String getModuleName();
    String getModuleId();
    String getModuleIdx();
    String getSkin();
    String getBrowserTitle();

    /**
     * @return 대상 모듈의 상대 경로
     */
    String getModulePath();
    String getTemplate();
    String getTemplatePath();
    String getTemplateFile();
}
