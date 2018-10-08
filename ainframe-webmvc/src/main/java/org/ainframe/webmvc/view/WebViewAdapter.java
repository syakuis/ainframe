package org.ainframe.webmvc.view;

import org.ainframe.web.config.model.Config;
import org.ainframe.webmvc.config.WebProperties;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 8.
 */
public interface WebViewAdapter {
  /**
   * {@link WebProperties#getAdminSkin()}
   * @return adminSkin
   */
  String getDefaultAdminSkin();

  /**
   * {@link WebProperties#getSkin()}
   * @return skin
   */
  String getDefaultSkin();

  /**
   * {@link WebProperties#getAdminLayoutIdx()}
   * @return layoutIdx
   */
  String getDefaultAdminLayoutIdx();

  /**
   * {@link org.ainframe.webmvc.support.freemarker.TemplateFinder#exists(String)}
   * @param template template path
   * @return 템플릿 존재유무
   */
  boolean isTemplateExists(String template);

  /**
   * {@link Config#getSkin()}
   * @return skin
   */
  String getConfigSkin();

  /**
   * {@link Config#getBasicSkin()}
   * @return basicSkin
   */
  String getConfigBasicSkin();

  /**
   * {@link Config#isBrowserTitleOverwrite()}
   * @return isBrowserTitleOverwrite
   */
  boolean isConfigBrowserTitleOverwrite();

  /**
   * {@link Config#getLayoutIdx()}
   * @return layoutIdx
   */
  String getConfigLayoutIdx();
}
