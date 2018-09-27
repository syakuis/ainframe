package org.ainframe.web.view;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 9. 27.
 */
public interface LayoutView {
    void changeLayout(String layout);

    void changeLayoutTemplate(String layoutTemplate);

    void changeLayoutAndTemplate(String layoutName, String layoutTemplate);

    org.ainframe.context.Layout getLayout();

    org.ainframe.web.menu.model.Menu getMenu();

    String getLayoutPath();

    String getLayoutFile();
}
