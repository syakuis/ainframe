package org.ainframe.webmvc.view;

import lombok.Builder;
import org.ainframe.web.config.model.Config;
import org.ainframe.webmvc.config.WebProperties;
import org.ainframe.webmvc.support.freemarker.TemplateFinder;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 8.
 */
@Builder
public class DefaultWebViewAdapter implements WebViewAdapter {
  private final WebProperties webProperties;
  private final Config config;
  private final TemplateFinder templateFinder;

  @Override
  public String getDefaultAdminSkin() {
    return webProperties.getAdminSkin();
  }

  @Override
  public String getDefaultSkin() {
    return webProperties.getSkin();
  }

  @Override
  public String getDefaultAdminLayoutIdx() {
    return webProperties.getAdminLayoutIdx();
  }

  @Override
  public boolean isTemplateExists(String template) {
    return templateFinder.exists(template);
  }

  @Override
  public String getConfigSkin() {
    return config.getSkin();
  }

  @Override
  public String getConfigBasicSkin() {
    return config.getBasicSkin();
  }

  @Override
  public boolean isConfigBrowserTitleOverwrite() {
    return config.isBrowserTitleOverwrite();
  }

  @Override
  public String getConfigLayoutIdx() {
    return config.getLayoutIdx();
  }
}
