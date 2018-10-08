package org.ainframe.webmvc.support.freemarker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 8.
 */
@Slf4j
public class TemplateFinder {
  private final FreeMarkerConfigurer freeMarkerConfigurer;

  public TemplateFinder(FreeMarkerConfigurer freeMarkerConfigurer) {
    this.freeMarkerConfigurer = freeMarkerConfigurer;
  }

  public boolean exists(String template) {
    try {
      this.freeMarkerConfigurer.getConfiguration().getTemplate(template);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
      return false;
    }

    return true;
  }
}
