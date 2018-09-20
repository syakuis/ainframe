package org.ainframe.web.config.model;

import java.io.Serializable;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 8. 31.
 */
public interface Config extends Serializable {
    String getModuleIdx();

    String getTitle();

    org.ainframe.core.data.enums.YesOrNo getTitleOverwrite();

    String getIndexPage();

    String getLayoutIdx();

    String getBasicSkin();

    String getSkin();

    String getStyleTheme();
}
