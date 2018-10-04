package org.ainframe.webmvc.view;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 27.
 */
public interface ModuleView {
    void changeBrowserTitle(String browserTitle);

    void changeBrowserTitle(String browserTitle, boolean must);

    void changeTemplate(String template);

    void changeSkin(String skin);

  /**
   * admin 템플릿으로 변경하여도 admin 레이아웃으로 자동 변경되지 않는 다.
   * 스킨 및 템플릿 변경에서는 실제 템플릿이 존재하는지를 검사하지 않는 다.
   * @param skin
   * @param template
   */
    void changeSkinAndTemplate(String skin, String template);

    org.ainframe.context.module.Module getModule();

    org.ainframe.context.module.Module getParentModule();

    String getModulePath();

    String getBrowserTitle();

    String getTemplatePath();

    String getTemplateFile();
}
