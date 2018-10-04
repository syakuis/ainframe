package org.ainframe.webmvc.view;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 1.
 */
public interface WebView {
    void addObject(String attributeName, Object attributeValue);

    ModelAndView done();

    boolean isOnlyUseModule();

    boolean isDisableLayout();

    boolean isDisableMenu();

    void setOnlyUseModule(boolean onlyUseModule);

    void setDisableLayout(boolean disableLayout);

    void setDisableMenu(boolean disableMenu);
}
