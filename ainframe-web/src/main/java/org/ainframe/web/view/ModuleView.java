package org.ainframe.web.view;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 27.
 */
public interface ModuleView {
    void changeBrowserTitle(String browserTitle);

    void changeBrowserTitle(String browserTitle, boolean must);

    void changeTemplate(String template);

    void changeSkin(String skin);

    void changeSkinAndTemplate(String skin, String template);

    org.ainframe.context.module.Module getModule();

    org.ainframe.context.module.Module getParentModule();

    String getModulePath();

    String getBrowserTitle();

    String getTemplatePath();

    String getTemplateFile();
}
