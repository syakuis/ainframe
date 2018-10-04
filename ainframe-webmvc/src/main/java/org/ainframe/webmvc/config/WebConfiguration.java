package org.ainframe.webmvc.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 1.
 */
@Slf4j
@Configuration
@ComponentScan("org.ainframe")
public class WebConfiguration extends WebMvcConfigurerAdapter {
  @Autowired
  private WebProperties webProperties;

  @Autowired
  private ResourceProperties resourceProperties;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (StringUtils.isNotEmpty(webProperties.getStaticsResourceLocation())) {
      registry.addResourceHandler("/statics/**")
        .addResourceLocations(webProperties.getStaticsResourceLocation())
        .setCachePeriod(resourceProperties.getCachePeriod());
    }
  }
}
